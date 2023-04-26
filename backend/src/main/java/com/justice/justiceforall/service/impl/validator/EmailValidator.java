package com.justice.justiceforall.service.impl.validator;

import com.justice.justiceforall.exception.InvalidUserFieldException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EmailValidator {
  private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

  public void validate(String email) {
    if (email == null || !email.matches(EMAIL_REGEX)) {
      throw new InvalidUserFieldException("The User Email is not properly formatted");
    }
  }
}
