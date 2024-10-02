package com.docker.demo.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "goals")
public class Goal {

  @Transient
  public static final String SEQUENCE_NAME = "goal_sequence";

  @Id
  private String id;

  @NotBlank(message = "text is missing or blank")
  private String text;
}