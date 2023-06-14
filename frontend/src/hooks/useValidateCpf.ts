import { IFormValues } from '@/components/molecules/SignupForm';
import { UseFormRegister } from 'react-hook-form';

export function useValidateCpf(register: UseFormRegister<IFormValues>) {
	return {
		cpfInputProps: register('cpf', {
			minLength: {
				value: 11,
				message: 'O CPF deve ter 11 caracteres'
			},
			maxLength: {
				value: 11,
				message: 'O CPF deve ter 11 caracteres'
			},
			pattern: {
				value: /^[0-9]+$/,
				message: 'O CPF deve conter apenas números'
			}
		})
	};
}