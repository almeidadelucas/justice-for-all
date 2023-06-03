package com.justice.justiceforall.service.userservice.uservalidator;

import com.justice.justiceforall.dto.userdto.CreateUserCommand;
import com.justice.justiceforall.exception.InvalidUserFieldException;
import com.justice.justiceforall.service.util.BaseCreatorHandler;

public class LastNameValidator extends BaseCreatorHandler<CreateUserCommand>  {

    @Override
    public void validate(CreateUserCommand input) {
        String lastName = input.lastName();
        if (lastName == null || lastName.length() < 2 || lastName.length() >= 100) {
            throw new InvalidUserFieldException("The Last Name has an invalid format");
        }
        toNext(input);
    }
}
