package com.justice.justiceforall.service.impl.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.justice.justiceforall.entity.UserType;
import com.justice.justiceforall.exception.InvalidUserFieldException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CPFValidatorTests {

  @Test
  void ensureACorrectCPFDoesNotThrowExceptionsWhenValidating() {
    var correctCpf = "19438274839";
    assertDoesNotThrow(() -> CPFValidator.validate(correctCpf, UserType.CLIENT));
  }

  @ParameterizedTest
  @ValueSource(strings = {"35938201-21", "4938576982", "123456789101"})
  void ensureABadFormattedCPFThrowsAnException(String invalidCpf) {
    assertThrows(InvalidUserFieldException.class,
        () -> CPFValidator.validate(invalidCpf, UserType.CLIENT));
  }

  @Test
  void ensureACpfIsNotValidatedForALawyer() {
    var incorrectCpf = "abc";
    assertDoesNotThrow(() -> CPFValidator.validate(incorrectCpf, UserType.LAWYER));
  }
}
