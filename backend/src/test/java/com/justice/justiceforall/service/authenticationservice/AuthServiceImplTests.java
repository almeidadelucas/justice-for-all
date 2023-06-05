package com.justice.justiceforall.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.justice.justiceforall.helper.AuthenticationResponseFixture;
import com.justice.justiceforall.helper.UserEntityFixture;
import com.justice.justiceforall.repository.UsersRepository;
import com.justice.justiceforall.service.authenticationservice.AuthService;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class AuthServiceImplTests {

  @Autowired
  private AuthService authService;

  @MockBean
  private UsersRepository usersRepository;

  private final String userEmail = "admin@email.com";
  private final String password = "password";

  private final String encodedAuth = new String(
      Base64.getEncoder().encode((userEmail + ":" + password).getBytes()), StandardCharsets.UTF_8);

  @Test
  void ensureAValidAuthenticationProperlyReturnsTheResponse() {
    var existingUser = UserEntityFixture.userWithId();
    when(usersRepository.findFirstsByEmailAndPassword(userEmail, password)).thenReturn(
        existingUser);
    var response = authService.validateBasicAuthorization("Basic " + encodedAuth);
    verify(usersRepository, times(1)).findFirstsByEmailAndPassword(userEmail, password);
    assertEquals(AuthenticationResponseFixture.validWithId(existingUser.getId()), response);
  }

  @Test
  void ensureAHeaderWithoutBasicKeywordHasInvalidAuthentication() {
    var response = authService.validateBasicAuthorization(encodedAuth);
    assertEquals(AuthenticationResponseFixture.invalidAuthentication(), response);
  }

  @Test
  void ensureAHeaderWithAnInvalidAuthenticationValueIsInvalid() {
    var invalidAuthentication = new String(
        Base64.getEncoder().encode((userEmail + ":" + password + ":" + userEmail).getBytes()),
        StandardCharsets.UTF_8);
    var response = authService.validateBasicAuthorization(invalidAuthentication);
    assertEquals(AuthenticationResponseFixture.invalidAuthentication(), response);
  }

  @Test
  void ensureTheAuthenticationIsInvalidIfTheUserDoesNotExistOnRepository() {
    when(usersRepository.findFirstsByEmailAndPassword(any(), any())).thenReturn(null);
    var response = authService.validateBasicAuthorization("Basic " + encodedAuth);
    verify(usersRepository, times(1)).findFirstsByEmailAndPassword(userEmail, password);
    assertEquals(AuthenticationResponseFixture.invalidAuthentication(), response);
  }

}
