package com.makingdevs.springreactive.controller.v1;

import com.makingdevs.springreactive.domain.Word;
import com.makingdevs.springreactive.service.IWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("WordsControllerV1")
@RequestMapping("api/v1/words")
@RequiredArgsConstructor
public class WordsController {

  private final IWordService wordService;

  @GetMapping
  public List<Word> findAllBlocking(Pageable pageable) throws Exception {
    return wordService.findAllBlocking(pageable);
  }

}
