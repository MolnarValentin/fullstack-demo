package com.docker.demo.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Setter
@Getter
@RequiredArgsConstructor
public class GoalApiException extends RuntimeException{

  private final String errorMessage;

  private final HttpStatusCode httpStatusCode;
}
