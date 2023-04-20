package com.justice.justiceforall.dto;

import com.justice.justiceforall.entity.UserType;

public record User(
    long userId,
    String firstName,
    String lastName,
    String email,
    String password,
    UserType type,
    String cpf,
    String oab
) {

}
