package com.makingdevs.springreactive.config;

import com.makingdevs.springreactive.domain.Word;
import com.makingdevs.springreactive.repository.WordReactiveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

  private final WordReactiveRepository wordReactiveRepository;
  @Value("classpath:words.txt")
  private Resource resource;

  @Override
  public void run(final ApplicationArguments args) {
    Mono<Long> monoCount = wordReactiveRepository.count();
    Long count = monoCount.block();

    if (!ObjectUtils.isEmpty(count) && count.equals(0L)) {
      Flux
        .fromStream(mapAllWords())
        .onErrorResume(Mono::error)
        .delayElements(Duration.ofMillis(1))
        .flatMap(wordReactiveRepository::save)
        .subscribe(w -> log.info("New word loaded: {}", w));
    }
  }

  private Stream<Word> mapAllWords() {
    Supplier<String> uuidSupplier = getUuidSupplier();
    Stream<Word> streamWord = Stream.empty();

    try {
      BufferedReader bufferedReader = new BufferedReader(
        new InputStreamReader(resource.getInputStream())
      );
      streamWord = bufferedReader
        .lines()
        .map(word -> new Word(uuidSupplier.get(), word));
    } catch (IOException ex) {
      log.error("Error reading word file {}", ex.getMessage());
    }

    return streamWord;
  }

  private Supplier<String> getUuidSupplier() {
    return () -> UUID.randomUUID().toString();
  }

}
