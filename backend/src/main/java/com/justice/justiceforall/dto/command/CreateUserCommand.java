package com.justice.justiceforall.dto.command;

import com.justice.justiceforall.entity.UserType;
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
}