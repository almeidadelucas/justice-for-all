package com.justice.justiceforall.service.impl.validator;

import com.justice.justiceforall.entity.UserType;
import com.justice.justiceforall.exception.InvalidUserFieldException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OABValidator {
  private static final String OAB_REGEX = "^[A-Z]{2}[0-9]{6}$";

  public void validate(String oab, UserType userType) {
    if (userType == UserType.LAWYER) {
      if (oab == null || !oab.matches(OAB_REGEX)) {
        throw new InvalidUserFieldException("The OAB does not have a valid format");
      }
    }
  }
}
