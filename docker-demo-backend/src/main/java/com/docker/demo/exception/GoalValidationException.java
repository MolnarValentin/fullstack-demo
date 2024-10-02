package com.docker.demo.exception;

import jakarta.validation.ValidationException;

public class GoalValidationException extends ValidationException {

  public GoalValidationException(String message) {
    super(message);
  }
}
