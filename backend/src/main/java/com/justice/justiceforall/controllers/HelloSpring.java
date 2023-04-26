package com.justice.justiceforall.controllers;

import com.justice.justiceforall.annotation.EndpointAuthentication;
import com.justice.justiceforall.config.AuthenticationType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//o path raiz é o localhost:8080

@RestController
public class HelloSpring {

  @EndpointAuthentication(authenticationType = AuthenticationType.NOT_AUTHENTICATED)
  @GetMapping(value = "/hello")
  public String getMethodName() {
    return "Oiiiiiiiiiiiie Spring";
  }

}