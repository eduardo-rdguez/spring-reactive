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
import java.util.UUID;
import java.util.function.Supplier;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

  private final WordReactiveRepository wordReactiveRepository;
  @Value("classpath:words.txt")
  private Resource resource;

  @Override
  public void run(final ApplicationArguments args) throws IOException {
    Mono<Long> monoCount = wordReactiveRepository.count();
    Long count = monoCount.block();

    if (!ObjectUtils.isEmpty(count) && count.equals(0L)) {
      Supplier<String> uuid = getUuidSupplier();

      BufferedReader bufferedReader = new BufferedReader(
        new InputStreamReader(resource.getInputStream())
      );

      Flux
        .fromStream(
          bufferedReader.lines().map(word -> wordReactiveRepository.save(new Word(uuid.get(), word)))
        )
        .subscribe(w -> log.info("New word loaded: {}", w.block()));

      log.info("Repository contains now {} entries.", wordReactiveRepository.count().block());
    }
  }

  private Supplier<String> getUuidSupplier() {
    return () -> UUID.randomUUID().toString();
  }

}
