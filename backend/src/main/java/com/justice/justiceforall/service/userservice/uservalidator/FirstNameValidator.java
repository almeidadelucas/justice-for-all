package com.justice.justiceforall.service.userservice.uservalidator;

import com.justice.justiceforall.dto.userdto.CreateUserCommand;
import com.justice.justiceforall.exception.InvalidUserFieldException;
import com.justice.justiceforall.service.util.BaseCreatorHandler;

public class FirstNameValidator extends BaseCreatorHandler<CreateUserCommand>  {

    @Override
    public void validate(CreateUserCommand input) {
        String firstName = input.firstName();
        if (firstName == null || firstName.length() < 2 || firstName.length() > 30) {
            throw new InvalidUserFieldException("The User first name is not properly formatted");
        }
        toNext(input);
    }
}
