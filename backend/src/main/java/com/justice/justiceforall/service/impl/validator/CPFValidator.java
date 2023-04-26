package com.justice.justiceforall.service.impl.validator;

import com.justice.justiceforall.entity.UserType;
import com.justice.justiceforall.exception.InvalidUserFieldException;
import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;

@UtilityClass
public class CPFValidator {

  private static final String CPF_REGEX = "^[0-9]{11}$";

  public void validate(String cpf, UserType userType) {
    if (userType == UserType.CLIENT) {
      if (cpf == null || !cpf.matches(CPF_REGEX)) {
        throw new InvalidUserFieldException("The CPF is not properly formatted");
      }
    }
  }
}
