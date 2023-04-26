package com.justice.justiceforall.service.impl.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.justice.justiceforall.exception.InvalidUserFieldException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class FirstNameValidatorTests {

  @Test
  void ensureACorrectFirstNameDoesNotThrowExceptionsWhenValidating() {
    var correctFirstName = "First Name";
    assertDoesNotThrow(() -> FirstNameValidator.validate(correctFirstName));
  }

  @ParameterizedTest
  @ValueSource(strings = {"a", "1234567890123456789012345678901"})
  void ensureABadFormattedFirstNameThrowsAnException(String invalidFirstName) {
    assertThrows(InvalidUserFieldException.class,
        () -> FirstNameValidator.validate(invalidFirstName));
  }

}
