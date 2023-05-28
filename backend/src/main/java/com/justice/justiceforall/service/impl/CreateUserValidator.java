package com.justice.justiceforall.service.impl;


import com.justice.justiceforall.dto.command.CreateUserCommand;
import com.justice.justiceforall.service.impl.validator.CPFValidator;
import com.justice.justiceforall.service.impl.validator.EmailValidator;
import com.justice.justiceforall.service.impl.validator.FirstNameValidator;
import com.justice.justiceforall.service.impl.validator.LastNameValidator;
import com.justice.justiceforall.service.impl.validator.OABValidator;
import com.justice.justiceforall.service.impl.validator.PasswordValidator;
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
