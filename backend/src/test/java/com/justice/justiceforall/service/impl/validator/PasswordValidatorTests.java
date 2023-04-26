package com.justice.justiceforall.service.impl.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.justice.justiceforall.exception.InvalidUserFieldException;
import org.junit.jupiter.api.Test;

public class PasswordValidatorTests {

  @Test
  void ensureACorrectPasswordDoesNotThrowExceptionsWhenValidating() {
    var correctPassword = "password";
    assertDoesNotThrow(() -> PasswordValidator.validate(correctPassword));
  }

  @Test
  void ensureAnInvalidPasswordThrowsAnException() {
    var invalidPassword = "1234567";
    assertThrows(InvalidUserFieldException.class,
        () -> PasswordValidator.validate(invalidPassword));
  }
}
