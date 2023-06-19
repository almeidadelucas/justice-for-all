import { IFormValues } from '@/components/molecules/SignupForm';
import { UseFormRegister } from 'react-hook-form';

export function useValidateLastName(register: UseFormRegister<IFormValues>) {
  return {
    lastNameInputProps: register('lastName', {
      minLength: {
        value: 2,
        message: 'O sobrenome deve ter no mínimo 2 caracteres',
      },
      maxLength: {
        value: 100,
        message: 'O sobrenome deve ter no máximo 100 caracteres',
      },
    }),
  };
}
