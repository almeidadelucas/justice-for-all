package com.justice.justiceforall.service.impl.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.justice.justiceforall.exception.InvalidUserFieldException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EmailValidatorTests {

  @Test
  void ensureACorrectEmailDoesNotThrowExceptionsWhenValidating() {
    var correctEmail = "email@email.com";
    assertDoesNotThrow(() -> EmailValidator.validate(correctEmail));
  }

  @ParameterizedTest
  @ValueSource(strings = {"hotmail.com", "@gmail.com", "email@gmail.coooom"})
  void ensureABadFormattedEmailThrowsAnException(String invalidEmail) {
    assertThrows(InvalidUserFieldException.class, () -> EmailValidator.validate(invalidEmail));
  }

}
