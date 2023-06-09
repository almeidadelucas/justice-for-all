package com.justice.justiceforall.controllers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class LoginControllerTests {

  @Autowired
  private LoginController loginController;

  @Test
  void ensureTheLoginEndpointIsProperlyExecuted() {
    assertDoesNotThrow(() -> loginController.login(1L));
  }
}
