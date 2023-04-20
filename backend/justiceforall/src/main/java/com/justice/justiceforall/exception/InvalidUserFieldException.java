package com.justice.justiceforall.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class InvalidUserFieldException extends ResponseStatusException {
  public InvalidUserFieldException(String message) {
    super(HttpStatusCode.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value()), message);
  }
}
