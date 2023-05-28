package com.justice.justiceforall.service.impl.validators;

import com.justice.justiceforall.dto.command.CreateUserCommand;
import com.justice.justiceforall.exception.InvalidUserFieldException;

public class PasswordValidator extends BaseUserCreatorHandler {

    @Override
    public void validate(CreateUserCommand input) {
        String password = input.password();
        if (password == null || password.length() < 8) {
            throw new InvalidUserFieldException("The password has less digits than the minimum");
        }
        toNext(input);
    }
}
