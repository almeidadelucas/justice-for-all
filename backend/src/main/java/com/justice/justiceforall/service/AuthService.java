package com.justice.justiceforall.service;

import com.justice.justiceforall.dto.AuthenticationResponse;

public interface AuthService {

  AuthenticationResponse validateBasicAuthorization(String basicAuthHeaderValue);
}
