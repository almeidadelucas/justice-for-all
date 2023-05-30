package com.justice.justiceforall.service.userservice.uservalidator;

import java.util.Arrays;

import com.justice.justiceforall.dto.userdto.CreateUserCommand;
import com.justice.justiceforall.entity.userentity.UserType;
import com.justice.justiceforall.exception.InvalidUserFieldException;

public class CPFValidator extends BaseUserCreatorHandler {

    private static final String CPF_REGEX = "^[0-9]{11}$";

    @Override
    public void validate(CreateUserCommand input) {
        if (input.type() == UserType.CLIENT) {
            String cpf = input.cpf();
            if (cpf == null || !cpf.matches(CPF_REGEX)) {
                throw new InvalidUserFieldException("The CPF is not properly formatted");
            } else {
                int[] digitosOriginais = new int[11];
                for (int i = 0; i < 11; i++) {
                    digitosOriginais[i] = Character.getNumericValue(cpf.charAt(i));
                }
                int[] digitosCopiados = Arrays.copyOf(digitosOriginais, digitosOriginais.length);
                /////primeiro digito
                int digito0 = digitoEsperado(digitosOriginais, 0);
                digitosCopiados[9] = digito0;
                /////segundo digito
                int digito1 = digitoEsperado(digitosCopiados, 1);
                digitosCopiados[10] = digito1;
                //////validação
                if (digitosOriginais[9] != digito0 || digitosOriginais[10] != digito1) {
                    throw new InvalidUserFieldException("The CPF is not properly formatted");
                }
            }
        }
        toNext(input);
    }

    private int digitoEsperado(int digitos[], int ordem) {
        int soma = 0;
        for (int i = 0; i < (9 + ordem); i++) {
            soma += digitos[i] * ((10 + ordem) - i);
        }
        int digitoEsperado = 11 - (soma % 11);
        if (digitoEsperado >= 10)
            digitoEsperado = 0;
        return digitoEsperado;
    }
}
