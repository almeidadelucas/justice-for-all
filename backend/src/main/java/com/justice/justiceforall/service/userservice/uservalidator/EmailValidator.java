package com.justice.justiceforall.service.userservice.uservalidator;

import com.justice.justiceforall.dto.userdto.CreateUserCommand;
import com.justice.justiceforall.exception.InvalidUserFieldException;
import com.justice.justiceforall.service.util.BaseCreatorHandler;

public class EmailValidator extends BaseCreatorHandler<CreateUserCommand>  {
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
