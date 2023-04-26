package com.justice.justiceforall.service.impl.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.justice.justiceforall.entity.UserType;
import com.justice.justiceforall.exception.InvalidUserFieldException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OABValidatorTests {

  @Test
  void ensureACorrectOABDoesNotThrowExceptionsWhenValidating() {
    var correctOab = "UF923459";
    assertDoesNotThrow(() -> OABValidator.validate(correctOab, UserType.LAWYER));
  }

  @ParameterizedTest
  @ValueSource(strings = {"Uf234859", "U234859", "UF24859", "UF2485932", "UFF285932"})
  void ensureABadFormattedOABThrowsAnException(String invalidOab) {
    assertThrows(InvalidUserFieldException.class,
        () -> OABValidator.validate(invalidOab, UserType.LAWYER));
  }

  @Test
  void ensureAOabIsNotValidatedForAClient() {
    var incorrectOab = "1230";
    assertDoesNotThrow(() -> OABValidator.validate(incorrectOab, UserType.CLIENT));
  }

}
