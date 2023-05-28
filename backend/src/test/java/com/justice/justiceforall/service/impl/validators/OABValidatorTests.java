package com.justice.justiceforall.service.impl.validators;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.justice.justiceforall.entity.UserType;
import com.justice.justiceforall.exception.InvalidUserFieldException;
import com.justice.justiceforall.helper.CreateUserCommandFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OABValidatorTests {

    @Test
    void ensureACorrectOABDoesNotThrowExceptionsWhenValidating() {
        var correctOab = "UF923459";
        var command = CreateUserCommandFixture.correctLawyerCommand().withOab(correctOab);
        assertDoesNotThrow(() -> new OABValidator().validate(command));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Uf234859", "U234859", "UF24859", "UF2485932", "UFF285932"})
    void ensureABadFormattedOABThrowsAnException(String invalidOab) {
        var command = CreateUserCommandFixture.correctLawyerCommand().withOab(invalidOab);
        assertThrows(InvalidUserFieldException.class,
                () -> new OABValidator().validate(command));
    }

    @Test
    void ensureAOabIsNotValidatedForAClient() {
        var incorrectOab = "1230";
        var command = CreateUserCommandFixture.correctClientCommand().withOab(incorrectOab);
        assertDoesNotThrow(() -> new OABValidator().validate(command));
    }

}
