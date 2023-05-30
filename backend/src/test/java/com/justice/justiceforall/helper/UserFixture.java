package com.justice.justiceforall.helper;

import com.justice.justiceforall.dto.userdto.User;
import com.justice.justiceforall.entity.userentity.UserType;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserFixture {
  public User correctUser() {
    return new User(
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
