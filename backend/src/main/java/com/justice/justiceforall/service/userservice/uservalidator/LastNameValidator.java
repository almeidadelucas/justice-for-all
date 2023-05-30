package com.justice.justiceforall.service.userservice.uservalidator;

import com.justice.justiceforall.dto.userdto.CreateUserCommand;
import com.justice.justiceforall.exception.InvalidUserFieldException;

public class LastNameValidator extends BaseUserCreatorHandler {

    @Override
    public void validate(CreateUserCommand input) {
        String lastName = input.lastName();
        if (lastName == null || lastName.length() < 2 || lastName.length() >= 100) {
            throw new InvalidUserFieldException("The Last Name has an invalid format");
        }
        toNext(input);
    }
}
