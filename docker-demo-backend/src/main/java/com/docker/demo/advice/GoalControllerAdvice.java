package com.docker.demo.advice;

import com.docker.demo.exception.ErrorResponse;
import com.docker.demo.exception.GoalApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GoalControllerAdvice {

  @ExceptionHandler(GoalApiException.class)
  public ResponseEntity<ErrorResponse> handleGoalApiException(GoalApiException exception) {

    ErrorResponse errorResponse = new ErrorResponse(exception.getHttpStatusCode().toString(),
        exception.getErrorMessage());

    return new ResponseEntity<>(errorResponse, exception.getHttpStatusCode());
  }
}
