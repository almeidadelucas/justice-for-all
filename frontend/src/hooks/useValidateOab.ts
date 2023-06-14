import { IFormValues } from '@/components/molecules/SignupForm';
import { UseFormRegister } from 'react-hook-form';

export function useValidateOab(register: UseFormRegister<IFormValues>) {
	return {
		oabInputProps: register('oab', {
			minLength: {
				value: 8,
				message: 'O número da OAB deve conter 8 caracteres'
			}, maxLength: {
				value: 8,
				message: 'O número da OAB deve conter 8 caracteres'
			},
			pattern: {
				value: /^[A-Z]{2}[0-9]{6}$/,
				message: 'OAB deve seguir o formato AA123456'
			}})
	};
}