package com.justice.justiceforall.annotation;

import com.justice.justiceforall.config.AuthenticationType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EndpointAuthentication {

  AuthenticationType authenticationType() default AuthenticationType.AUTHENTICATED;
}
