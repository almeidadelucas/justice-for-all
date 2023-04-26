package com.justice.justiceforall.controllers;

import com.justice.justiceforall.annotation.EndpointAuthentication;
import com.justice.justiceforall.config.AuthenticationType;
import com.justice.justiceforall.dto.User;
import com.justice.justiceforall.dto.command.CreateUserCommand;
import com.justice.justiceforall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  private final Logger logger = LoggerFactory.getLogger(UserController.class);

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @EndpointAuthentication(authenticationType = AuthenticationType.NOT_AUTHENTICATED)
  public final User createUser(@RequestBody CreateUserCommand createUserCommand) {
    logger.info("Received a request to create a new user of type {}", createUserCommand.type());
    return userService.createUser(createUserCommand);
  }
}
