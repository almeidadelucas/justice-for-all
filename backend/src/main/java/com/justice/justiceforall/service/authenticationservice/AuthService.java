package com.justice.justiceforall.service.authenticationservice;

import com.justice.justiceforall.dto.userdto.AuthenticationResponse;

public interface AuthService {

  AuthenticationResponse validateBasicAuthorization(String basicAuthHeaderValue);
}
