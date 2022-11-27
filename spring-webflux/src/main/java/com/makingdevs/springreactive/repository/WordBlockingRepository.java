package com.makingdevs.springreactive.repository;

import com.makingdevs.springreactive.domain.Word;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordBlockingRepository extends PagingAndSortingRepository<Word, String> {

  List<Word> findAll();

  List<Word> findAllByIdNotNull(Pageable pageable);

}
