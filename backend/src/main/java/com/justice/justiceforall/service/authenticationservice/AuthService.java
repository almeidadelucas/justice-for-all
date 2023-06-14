package com.justice.justiceforall.service.authenticationservice;

import com.justice.justiceforall.dto.userdto.AuthenticationResponse;
import com.justice.justiceforall.dto.userdto.User;

public interface AuthService {

  AuthenticationResponse validateBasicAuthorization(String basicAuthHeaderValue);

  User getUserFromId(Long userId);
}
