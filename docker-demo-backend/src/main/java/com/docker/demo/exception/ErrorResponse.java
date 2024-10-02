package com.docker.demo.exception;

import lombok.Data;

@Data
public class ErrorResponse {

  private final String code;

  private final String message;
}
