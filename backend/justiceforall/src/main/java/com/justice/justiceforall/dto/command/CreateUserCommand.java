package com.justice.justiceforall.dto.command;

import com.justice.justiceforall.entity.UserType;

public record CreateUserCommand(
    String firstName,
    String lastName,
    String email,
    String password,
    UserType type,
    String cpf,
    String oab
) {

}