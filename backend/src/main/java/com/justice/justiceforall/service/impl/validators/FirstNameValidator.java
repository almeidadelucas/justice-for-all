package com.justice.justiceforall.service.impl.validators;

import com.justice.justiceforall.dto.command.CreateUserCommand;
import com.justice.justiceforall.exception.InvalidUserFieldException;

public class FirstNameValidator extends BaseUserCreatorHandler {

    @Override
    public void validate(CreateUserCommand input) {
        String firstName = input.firstName();
        if (firstName == null || firstName.length() < 2 || firstName.length() > 30) {
            throw new InvalidUserFieldException("The User first name is not properly formatted");
        }
        toNext(input);
    }
}
