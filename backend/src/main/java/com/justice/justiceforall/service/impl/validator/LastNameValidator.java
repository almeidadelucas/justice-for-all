package com.justice.justiceforall.service.impl.validator;

import com.justice.justiceforall.exception.InvalidUserFieldException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LastNameValidator {

  public void validate(String lastName) {
    if (lastName == null || lastName.length() < 2 || lastName.length() >= 100) {
      throw new InvalidUserFieldException("The Last Name has an invalid format");
    }
  }
}
