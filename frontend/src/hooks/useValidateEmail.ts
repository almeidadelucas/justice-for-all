import { IFormValues } from '@/components/molecules/SignupForm';
import { UseFormRegister } from 'react-hook-form';

export function useValidateEmail(register: UseFormRegister<IFormValues>) {
  return {
    emailInputProps: register('email', {
      pattern: {
        value: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,
        message: 'Email deve seguir o formato "email@email.com"',
      },
    }),
  };
}
