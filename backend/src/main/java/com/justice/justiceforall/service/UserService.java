package com.justice.justiceforall.service;

import com.justice.justiceforall.dto.User;
import com.justice.justiceforall.dto.command.CreateUserCommand;

public interface UserService {
  User createUser(CreateUserCommand createUserCommand);
}
