package com.justice.justiceforall.service.userservice.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.justice.justiceforall.exception.InvalidUserFieldException;
import com.justice.justiceforall.helper.user.CreateUserCommandFixture;
import com.justice.justiceforall.service.userservice.uservalidator.PasswordValidator;

import org.junit.jupiter.api.Test;

public class PasswordValidatorTests {

    @Test
    void ensureACorrectPasswordDoesNotThrowExceptionsWhenValidating() {
        var correctPassword = "password";
        var command = CreateUserCommandFixture.correctClientCommand().withPassword(correctPassword);
        assertDoesNotThrow(() -> new PasswordValidator().validate(command));
    }

    @Test
    void ensureAnInvalidPasswordThrowsAnException() {
        var invalidPassword = "1234567";
        var command = CreateUserCommandFixture.correctClientCommand().withPassword(invalidPassword);
        assertThrows(InvalidUserFieldException.class,
                () -> new PasswordValidator().validate(command));
    }
}
