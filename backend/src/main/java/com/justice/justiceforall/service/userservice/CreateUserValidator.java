package com.justice.justiceforall.service.userservice;


import com.justice.justiceforall.dto.userdto.CreateUserCommand;
import com.justice.justiceforall.service.userservice.uservalidator.CPFValidator;
import com.justice.justiceforall.service.userservice.uservalidator.EmailValidator;
import com.justice.justiceforall.service.userservice.uservalidator.FirstNameValidator;
import com.justice.justiceforall.service.userservice.uservalidator.LastNameValidator;
import com.justice.justiceforall.service.userservice.uservalidator.OABValidator;
import com.justice.justiceforall.service.userservice.uservalidator.PasswordValidator;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CreateUserValidator {
    void validateNewUser(CreateUserCommand createUserCommand) {
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

        firstNameValidator.validate(createUserCommand);
    }
}
