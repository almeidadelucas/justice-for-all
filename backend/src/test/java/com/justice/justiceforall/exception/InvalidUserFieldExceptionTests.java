package com.justice.justiceforall.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class InvalidUserFieldExceptionTests {
  @Test
  void ensureTheInvalidUserFieldExceptionIsProperlyConfigured() {
    var message = "Message";
    var exception = new InvalidUserFieldException(message);
    assertTrue(exception instanceof ResponseStatusException);
    assertEquals(message, exception.getReason());
    assertEquals(
        HttpStatusCode.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value()),
        exception.getStatusCode()
    );
  }
}
