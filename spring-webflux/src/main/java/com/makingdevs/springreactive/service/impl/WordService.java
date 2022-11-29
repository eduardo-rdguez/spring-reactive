package com.makingdevs.springreactive.service.impl;

import com.makingdevs.springreactive.constant.Constants;
import com.makingdevs.springreactive.domain.Word;
import com.makingdevs.springreactive.repository.WordBlockingRepository;
import com.makingdevs.springreactive.repository.WordReactiveRepository;
import com.makingdevs.springreactive.service.IWordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WordService implements IWordService {

  private final WordReactiveRepository wordReactiveRepository;
  private final WordBlockingRepository wordBlockingRepository;

  public Flux<Word> findAllReactive(Pageable pageable) {
    log.info("Find all words - reactive");
    return wordReactiveRepository.findAllByIdNotNull(pageable)
      .delayElements(Duration.ofMillis(Constants.ONE_HUNDRED));
  }

  public List<Word> findAllBlocking(Pageable pageable) throws Exception {
    log.info("Find all words - blocking");
    Thread.sleep((long) Constants.ONE_HUNDRED * pageable.getPageSize());
    return wordBlockingRepository.findAllByIdNotNull(pageable);
  }

}
