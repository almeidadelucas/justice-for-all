package com.justice.justiceforall.service.impl.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.justice.justiceforall.exception.InvalidUserFieldException;
import com.justice.justiceforall.helper.CreateUserCommandFixture;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LastNameValidatorTests {

    @Test
    void ensureACorrectLastNameDoesNotThrowExceptionsWhenValidating() {
        var correctLastName = "Last Name";
        var command = CreateUserCommandFixture.correctClientCommand().withLastName(correctLastName);
        assertDoesNotThrow(() -> new LastNameValidator().validate(command));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 101})
    void ensureALastNameWithInvalidLengthThrowsAnException(int invalidLastNameLength) {
        var invalidLastName = RandomStringUtils.random(invalidLastNameLength);
        var command = CreateUserCommandFixture.correctClientCommand().withLastName(invalidLastName);
        assertThrows(InvalidUserFieldException.class,
                () -> new LastNameValidator().validate(command));
    }
}
