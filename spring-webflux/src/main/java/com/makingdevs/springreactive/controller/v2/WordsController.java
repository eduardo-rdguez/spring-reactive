package com.makingdevs.springreactive.controller.v2;

import com.makingdevs.springreactive.domain.Word;
import com.makingdevs.springreactive.service.IWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController("WordsControllerV2")
@RequestMapping("api/v2/users")
@RequiredArgsConstructor
public class WordsController {

  private final IWordService userService;

  @GetMapping(value = "", produces = "text/event-stream")
  public Flux<Word> findAllReactive(Pageable pageable) {
    return userService.findAllReactive(pageable);
  }

}
