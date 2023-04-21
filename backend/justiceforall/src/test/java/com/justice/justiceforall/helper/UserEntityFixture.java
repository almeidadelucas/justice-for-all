package com.justice.justiceforall.helper;

import com.justice.justiceforall.entity.UserEntity;
import com.justice.justiceforall.entity.UserType;
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
        "10439284154",
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
        "10439284154",
        null
    );
  }
}
