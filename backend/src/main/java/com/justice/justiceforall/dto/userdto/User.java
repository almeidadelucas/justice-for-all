package com.justice.justiceforall.dto.userdto;

import com.justice.justiceforall.entity.userentity.UserEntity;
import com.justice.justiceforall.entity.userentity.UserType;
import lombok.With;

@With
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
    public User(UserEntity userEntity) {
        this(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getUserType(),
                userEntity.getCpf(),
                userEntity.getOab()
        );
    }
}
