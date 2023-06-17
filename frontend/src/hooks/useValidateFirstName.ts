import { IFormValues } from '@/components/molecules/SignupForm';
import { UseFormRegister } from 'react-hook-form';

export function useValidateFirstName(register: UseFormRegister<IFormValues>) {
  return {
    firstNameInputProps: register('firstName', {
      minLength: {
        value: 2,
        message: 'O nome deve ter no mínimo 2 caracteres',
      },
      maxLength: {
        value: 30,
        message: 'O nome deve ter no máximo 30 caracteres',
      },
    }),
  };
}
