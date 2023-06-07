package com.justice.justiceforall.dto.userdto;

import com.justice.justiceforall.entity.userentity.UserType;

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
