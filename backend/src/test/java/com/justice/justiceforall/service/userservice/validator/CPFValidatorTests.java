package com.justice.justiceforall.service.userservice.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.justice.justiceforall.exception.InvalidUserFieldException;
import com.justice.justiceforall.helper.user.CreateUserCommandFixture;
import com.justice.justiceforall.service.userservice.uservalidator.CPFValidator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CPFValidatorTests {

    @Test
    void ensureACorrectCPFDoesNotThrowExceptionsWhenValidating() {
        var command = CreateUserCommandFixture.correctClientCommand().withCpf("72169247114");
        assertDoesNotThrow(() -> new CPFValidator().validate(command));
    }

    @ParameterizedTest
    @ValueSource(strings = {"35938201-21", "4938576982", "123456789101", "123"})
    void ensureABadFormattedCPFThrowsAnException(String invalidCpf) {
        var command = CreateUserCommandFixture.correctClientCommand().withCpf(invalidCpf);
        assertThrows(InvalidUserFieldException.class,
                () -> new CPFValidator().validate(command));
    }

    @ParameterizedTest
    @ValueSource(strings = {"24836425646", "19438274839", "35938201212"})
    void ensureInvalidCPFWithCorrectFormattingThrowsAnException(String invalidCpf) {
        var command = CreateUserCommandFixture.correctClientCommand().withCpf(invalidCpf);
        assertThrows(InvalidUserFieldException.class,
                () -> new CPFValidator().validate(command));
    }

    @Test
    void ensureACpfIsNotValidatedForALawyer() {
        var command = CreateUserCommandFixture.correctLawyerCommand().withCpf("abc");
        assertDoesNotThrow(() -> new CPFValidator().validate(command));
    }
}
