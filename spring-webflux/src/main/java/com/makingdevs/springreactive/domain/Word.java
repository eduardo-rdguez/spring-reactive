package com.makingdevs.springreactive.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Document(value = "words")
public class Word {

  @Id
  private String id;

  @NotBlank
  private String value;

  public Word(String id, String value) {
    this.id = id;
    this.value = value;
  }

}
