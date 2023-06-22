import { IFormValues } from '@/components/molecules/SignupForm';
import { UseFormRegister } from 'react-hook-form';

export function useValidatePassword(register: UseFormRegister<IFormValues>) {
  return {
    passwordInputProps: register('password', {
      minLength: {
        value: 8,
        message: 'A senha deve ter no mínimo 8 dígitos',
      },
    }),
  };
}
