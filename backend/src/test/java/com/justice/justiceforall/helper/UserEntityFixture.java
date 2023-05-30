package com.justice.justiceforall.helper;

import com.justice.justiceforall.entity.userentity.UserEntity;
import com.justice.justiceforall.entity.userentity.UserType;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserEntityFixture {

  public UserEntity userWithoutId() {
    return new UserEntity(
        null,
        "First Name",
        "Last Name",
        "Email@email.com",
        "password",
        UserType.CLIENT,
        "32227526726",
        null
    );
  }

  public UserEntity userWithId() {
    return new UserEntity(
        1L,
        "First Name",
        "Last Name",
        "Email@email.com",
        "password",
        UserType.CLIENT,
        "32227526726",
        null
    );
  }
}
