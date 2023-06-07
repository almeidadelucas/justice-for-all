package com.justice.justiceforall.service.authenticationservice;

import com.justice.justiceforall.dto.userdto.AuthenticationResponse;
import com.justice.justiceforall.entity.userentity.UserEntity;
import com.justice.justiceforall.repository.UsersRepository;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

  @Autowired
  private UsersRepository usersRepository;

  private final Logger logger = LoggerFactory.getLogger(AuthService.class);

  @Override
  public AuthenticationResponse validateBasicAuthorization(String basicAuthHeaderValue) {
    if (basicAuthHeaderValue != null && basicAuthHeaderValue.toLowerCase().startsWith("basic ")) {
      String[] decodedCredentials = getDecodedCredentials(basicAuthHeaderValue);
      if (decodedCredentials.length == 2) {
        logger.info("The credentials were decoded. Now checking if the credentials exist...");
        UserEntity user = getUserWithCredentials(decodedCredentials);
        if (user != null) {
          logger.info("Authentication was validated for the user with Id {}", user.getId());
          return new AuthenticationResponse(true, user.getId());
        }
      }
    }
    logger.error("Invalid authentication for authorization header [{}]", basicAuthHeaderValue);
    return new AuthenticationResponse(false, null);
  }

  private static String[] getDecodedCredentials(String basicAuthHeaderValue) {
    var credentials = basicAuthHeaderValue.substring(5).trim();
    return new String(Base64.getDecoder().decode(credentials), StandardCharsets.UTF_8).split(":");
  }

  private UserEntity getUserWithCredentials(String[] decodedCredentials) {
    var email = decodedCredentials[0];
    var password = decodedCredentials[1];
    return usersRepository.findFirstsByEmailAndPassword(email, password);
  }
}
