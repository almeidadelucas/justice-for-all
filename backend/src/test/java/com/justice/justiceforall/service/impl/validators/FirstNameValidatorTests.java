package com.justice.justiceforall.service.impl.validators;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.justice.justiceforall.exception.InvalidUserFieldException;
import com.justice.justiceforall.helper.CreateUserCommandFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class FirstNameValidatorTests {

    @Test
    void ensureACorrectFirstNameDoesNotThrowExceptionsWhenValidating() {
        var correctFirstName = "First Name";
        var command = CreateUserCommandFixture.correctClientCommand().withFirstName(correctFirstName);
        assertDoesNotThrow(() -> new FirstNameValidator().validate(command));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "1234567890123456789012345678901"})
    void ensureABadFormattedFirstNameThrowsAnException(String invalidFirstName) {
        var command = CreateUserCommandFixture.correctClientCommand().withFirstName(invalidFirstName);
        assertThrows(InvalidUserFieldException.class,
                () -> new FirstNameValidator().validate(command));
    }

}
