package com.justice.justiceforall.service.impl.validator;

import com.justice.justiceforall.exception.InvalidUserFieldException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PasswordValidator {
  public void validate(String password) {
    if (password == null || password.length() < 8) {
      throw new InvalidUserFieldException("The password has less digits than the minimum");
    }
  }
}
