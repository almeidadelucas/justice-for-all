package com.justice.justiceforall.service.userservice.uservalidator;

import com.justice.justiceforall.dto.userdto.CreateUserCommand;
import com.justice.justiceforall.exception.InvalidUserFieldException;
import com.justice.justiceforall.service.util.BaseCreatorHandler;

public class PasswordValidator extends BaseCreatorHandler<CreateUserCommand>  {

    @Override
    public void validate(CreateUserCommand input) {
        String password = input.password();
        if (password == null || password.length() < 8) {
            throw new InvalidUserFieldException("The password has less digits than the minimum");
        }
        toNext(input);
    }
}
