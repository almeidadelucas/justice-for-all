package com.justice.justiceforall.service.userservice.uservalidator;

import com.justice.justiceforall.dto.userdto.CreateUserCommand;
import com.justice.justiceforall.entity.userentity.UserType;
import com.justice.justiceforall.exception.InvalidUserFieldException;

public class OABValidator extends BaseUserCreatorHandler {
    private static final String OAB_REGEX = "^[A-Z]{2}[0-9]{6}$";

    @Override
    public void validate(CreateUserCommand input) {
        if (input.type() == UserType.LAWYER) {
            String oab = input.oab();
            if (oab == null || !oab.matches(OAB_REGEX)) {
                throw new InvalidUserFieldException("The OAB does not have a valid format");
            }
        }
        toNext(input);
    }
}
