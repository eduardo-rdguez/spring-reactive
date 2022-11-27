package com.makingdevs.springreactive.service;

import com.makingdevs.springreactive.domain.Word;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;

import java.util.List;

public interface IWordService {

  Flux<Word> findAllReactive(Pageable pageable);

  List<Word> findAllBlocking(Pageable pageable) throws Exception;

}
