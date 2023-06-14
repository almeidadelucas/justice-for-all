package com.justice.justiceforall.controllers;

import com.justice.justiceforall.constants.AttributeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

  private final Logger logger = LoggerFactory.getLogger(LoginController.class);

  @PostMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void login(@RequestAttribute(AttributeConstants.AUTHENTICATED_USER_ID) Long userId) {
    logger.info("The user with Id {} was successfully authenticated!", userId);
  }
}
