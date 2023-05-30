package com.justice.justiceforall.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.justice.justiceforall.helper.CreateUserCommandFixture;
import com.justice.justiceforall.helper.UserFixture;
import com.justice.justiceforall.service.userservice.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UserControllerTests {

  @Autowired
  private UserController userController;

  @MockBean
  private UserService userService;

  @Test
  void ensureTheControllerCallsTheServiceMethod() {
    var createUserCommand = CreateUserCommandFixture.correctClientCommand();
    var user = UserFixture.correctUser();
    when(userService.createUser(any())).thenReturn(user);
    var response = userController.createUser(createUserCommand);
    verify(userService, times(1)).createUser(createUserCommand);
    assertEquals(user, response);
  }
}
