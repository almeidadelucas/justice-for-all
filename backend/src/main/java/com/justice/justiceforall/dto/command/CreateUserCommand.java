package com.justice.justiceforall.dto.command;

import com.justice.justiceforall.entity.UserType;
import com.justice.justiceforall.service.impl.validator.*;
import lombok.With;

@With
public record CreateUserCommand(
        String firstName,
        String lastName,
        String email,
        String password,
        UserType type,
        String cpf,
        String oab
) {
    public void validateNewUser() {
        var firstNameValidator = new FirstNameValidator();
        var lastNameValidator = new LastNameValidator();
        var emailValidator = new EmailValidator();
        var passwordValidator = new PasswordValidator();
        var cpfValidator = new CPFValidator();
        var oabValidator = new OABValidator();

        firstNameValidator.setNext(lastNameValidator);
        lastNameValidator.setNext(emailValidator);
        emailValidator.setNext(passwordValidator);
        passwordValidator.setNext(cpfValidator);
        cpfValidator.setNext(oabValidator);

        firstNameValidator.validate(this);
    }
}