package com.makingdevs.springreactive.repository;

import com.makingdevs.springreactive.domain.Word;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface WordReactiveRepository extends ReactiveSortingRepository<Word, String> {

  Flux<Word> findAllByIdNotNull(Pageable pageable);

}
