package com.justice.justiceforall.config.interceptor;

import com.justice.justiceforall.annotation.EndpointAuthentication;
import com.justice.justiceforall.config.AuthenticationType;
import com.justice.justiceforall.constants.AttributeConstants;
import com.justice.justiceforall.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

  @Autowired
  private AuthService authService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    if (!endpointHasAuthenticationEnabled((HandlerMethod) handler)) {
      return true;
    }
    var authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
    var authenticationResponse = authService.validateBasicAuthorization(authorization);
    if (authenticationResponse.isAuthorized()) {
      request.setAttribute(AttributeConstants.AUTHENTICATED_USER_ID,
          authenticationResponse.userId().toString());
      return true;
    }
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    return false;
  }

  private static boolean endpointHasAuthenticationEnabled(HandlerMethod handler) {
    var endpointAuthentication =
        handler.getMethod().getAnnotation((EndpointAuthentication.class));
    if (endpointAuthentication == null
        || endpointAuthentication.authenticationType() == AuthenticationType.AUTHENTICATED) {
      return true;
    }
    return false;
  }

}
