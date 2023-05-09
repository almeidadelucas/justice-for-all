import React from 'react';
import styled from '@emotion/styled';
import { TextField, Box, Typography, Button, FormControl, FormLabel, RadioGroup, FormControlLabel, Radio, FormHelperText } from '@mui/material';
import { useRouter } from 'next/router';
import { FormEvent, useState } from 'react';

const UserType = {
	LAWYER: 'LAWYER',
	CLIENT: 'CLIENT'
};

interface IFormValues {
	firstName: string;
	lastName: string;
	email: string;
	password: string;
	type: keyof typeof UserType | '';
	cpf?: string;
	oab?: string;
}

const Form = styled.form`
	display: flex;
	flex-direction: column;
	row-gap: 1rem;
`;

const EMAIL_REGEX =
	/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

const OAB_REGEX = /^[A-Z]{2}[0-9]{6}$/;

export default function SingIn() {
	const { push } = useRouter();

	const [formValues, setFormValues] = useState<IFormValues>({
		firstName: '',
		lastName: '',
		email: '',
		password: '',
		type: ''
	});

	const [errors, setErrors] = useState<IFormValues>({
		firstName: '',
		lastName: '',
		email: '',
		password: '',
		type: '',
		cpf: '',
		oab: '',
	});

	const handleChange = (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
		const fieldName =  event.target.name;
		const fieldValue = event.target.value;

		setFormValues(current => ({
			...current,
			[fieldName]: fieldValue
		}));
	};

	const validate = () => {
		const newErrors: IFormValues = {
			firstName: '',
			lastName: '',
			email: '',
			password: '',
			type: '',
			cpf: '',
			oab: '',
		};

		if (!formValues.email.match(EMAIL_REGEX)) {
			newErrors.email = 'Email deve seguir o formato "email@email.com"';
		}
		if (formValues.firstName.length < 2 || formValues.firstName.length > 30) {
			newErrors.firstName = 'O primeiro nome deve conter mais que duas letras e menos de 30';
		}
		if (formValues.lastName.length < 2 || formValues.lastName.length > 100) {
			newErrors.lastName = 'O sobrenome nome deve conter mais que duas letras e menos de 100';
		} 
		if (formValues.cpf && formValues.cpf?.length !== 11) {
			newErrors.cpf = 'CPF deve conter 11 dígitos';
		}
		if (formValues.oab) {
			if (formValues.oab.length !== 8) {
				newErrors.oab = 'OAB deve conter 8 dígitos';
			} else if (!formValues.oab.match(OAB_REGEX)) {
				newErrors.oab = 'OAB deve seguir o formato AA123456';
			}
		}
		if (formValues.password.length < 8) {
			newErrors.password = 'A senha deve conter no mínimo 8 dígitos';
		}

		setErrors(newErrors);
		return Object.values(newErrors).every(value => value === '');
	};

	const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
		event.preventDefault();
		if (validate()) {
			fetch('http://localhost:8080/user', {
				method: 'POST',
				body: JSON.stringify(formValues),
				headers: {
					'Content-Type': 'application/json'
				}
			}).then(res => {
				if (res.status === 201) {
					push('/');
				} else {
					alert('Erro ao cadastrar usuário');
				}
			}).catch(err => {
				alert('Erro ao cadastrar usuário');
				console.error('Error to register new user: ', err.message);
			});
		}
	};

	return (
		<Box
			sx={{
				width: '100vw',
				height: '100vh',
				display: 'flex',
				justifyContent: 'center',
				alignItems: 'center',
			}}
		>
			<Box
				sx={{
					display: 'flex',
					flexDirection: 'column',
					padding: '2rem',
					border: '1px solid black',
					borderRadius: '3%',
				}}
			>
				<Typography variant="h4" component="h1" sx={{ marginBottom: '1rem' }}>
					Cadastre-se na nossa plataforma!
				</Typography>
				<Form onSubmit={handleSubmit}>
					<Box
						sx={{
							display: 'flex',
							flexDirection: 'column',
							rowGap: '0.5rem',
						}}
					>
						<Box sx={{
							display: 'flex',
							'& > *': {
								flex: 1
							},
							columnGap: '1rem'
						}}>
							<TextField
								name="firstName"
								label="Primeiro Nome"
								required
								variant="standard"
								error={!!errors.firstName}
								helperText={errors.firstName}
								onChange={handleChange}
							/>
							<TextField
								name="lastName"
								label="Sobrenome"
								required
								variant="standard"
								error={!!errors.lastName}
								helperText={errors.lastName}
								onChange={handleChange}
							/>
						</Box>
						<TextField
							name="email"
							label="Email"
							required
							variant="standard"
							error={!!errors.email}
							helperText={errors.email}
							onChange={handleChange}
						/>
						<TextField
							name="password"
							label="Senha"
							required
							variant="standard"
							type="password"
							error={!!errors.password}
							helperText={errors.password}
							onChange={handleChange}
						/>
						<FormControl sx={{ borderBottom: '1px solid grey'}} required>
							<FormLabel>Tipo de usuário</FormLabel>
							<RadioGroup row onChange={handleChange}>
								<FormControlLabel value={UserType.LAWYER} control={<Radio />} label="Advogado" name="type" />
								<FormControlLabel value={UserType.CLIENT} control={<Radio />} label="Cliente" name="type" />
							</RadioGroup>
						</FormControl>
						{formValues.type === UserType.LAWYER && (<TextField
							name="oab"
							label="OAB"
							required
							variant="standard"
							error={!!errors.oab}
							helperText={errors.oab}
							onChange={handleChange}
						/>)}
						{formValues.type === UserType.CLIENT && (<TextField
							name="cpf"
							label="CPF"
							required
							variant="standard"
							error={!!errors.cpf}
							helperText={errors.cpf}
							onChange={handleChange}
						/>)}
					</Box>
					<Button type="submit" variant="outlined">
							Cadastrar
					</Button>
				</Form>
			</Box>
		</Box>
	);
}
