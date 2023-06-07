package com.justice.justiceforall.service.userservice;

import com.justice.justiceforall.dto.userdto.CreateUserCommand;
import com.justice.justiceforall.dto.userdto.User;

public interface UserService {
  User createUser(CreateUserCommand createUserCommand);
}
