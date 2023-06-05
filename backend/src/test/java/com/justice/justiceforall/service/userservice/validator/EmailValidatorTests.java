package com.justice.justiceforall.service.impl.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.justice.justiceforall.exception.InvalidUserFieldException;
import com.justice.justiceforall.helper.CreateUserCommandFixture;
import com.justice.justiceforall.service.userservice.uservalidator.EmailValidator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EmailValidatorTests {

    @Test
    void ensureACorrectEmailDoesNotThrowExceptionsWhenValidating() {
        var correctEmail = "email@email.com";
        var command = CreateUserCommandFixture.correctClientCommand().withEmail(correctEmail);
        assertDoesNotThrow(() -> new EmailValidator().validate(command));
    }

    @ParameterizedTest
    @ValueSource(strings = {"hotmail.com", "@gmail.com", "email@gmail.coooom"})
    void ensureABadFormattedEmailThrowsAnException(String invalidEmail) {
        var command = CreateUserCommandFixture.correctClientCommand().withEmail(invalidEmail);
        assertThrows(InvalidUserFieldException.class, () -> new EmailValidator().validate(command));
    }

}
