package com.justice.justiceforall.helper;

import com.justice.justiceforall.dto.userdto.AuthenticationResponse;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthenticationResponseFixture {

  public AuthenticationResponse invalidAuthentication() {
    return new AuthenticationResponse(false, null);
  }

  public AuthenticationResponse validWithId(Long id) {
    return new AuthenticationResponse(true, id);
  }
}
