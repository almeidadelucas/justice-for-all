package com.justice.justiceforall.service.impl.validators;

import com.justice.justiceforall.dto.command.CreateUserCommand;
import com.justice.justiceforall.exception.InvalidUserFieldException;

public class EmailValidator extends BaseUserCreatorHandler {
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    @Override
    public void validate(CreateUserCommand input) {
        String email = input.email();
        if (email == null || !email.matches(EMAIL_REGEX)) {
            throw new InvalidUserFieldException("The User Email is not properly formatted");
        }
        toNext(input);
    }
}
