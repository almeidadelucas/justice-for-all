package com.justice.justiceforall.service.impl.validator;

import com.justice.justiceforall.exception.InvalidUserFieldException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FirstNameValidator {
  public void validate(String firstName) {
    if (firstName == null || firstName.length() < 2 || firstName.length() > 30) {
      throw new InvalidUserFieldException("The User first name is not properly formatted");
    }
  }
}
