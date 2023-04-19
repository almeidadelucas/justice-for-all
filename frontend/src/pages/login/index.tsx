import React from 'react';
import styled from '@emotion/styled';
import { TextField, Box, Typography, Button } from '@mui/material';
import { useRouter } from 'next/router';
import { FormEvent, useState } from 'react';

interface IUser {
	email: string;
	password: string;
}

const Form = styled.form`
	display: flex;
	flex-direction: column;
	row-gap: 1rem;
`;

const EMAIL_REGEX =
	/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

export default function Login() {
	const { push } = useRouter();

	const [user, setUser] = useState<IUser>({
		email: '',
		password: '',
	});
	const [errors, setErrors] = useState<IUser>({
		email: '',
		password: '',
	});

	const handleChange = (key: keyof IUser, value: string) => {
		setUser({ ...user, [key]: value });
	};

	const validate = () => {
		if (!user.email.match(EMAIL_REGEX)) {
			setErrors({
				...errors,
				email: 'Email deve ter o formato "email@email.com"',
			});
			return false;
		}
		return true;
	};

	const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
		event.preventDefault();
		if (validate()) {
			alert('Faz o login');
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
					Olá! Boas vindas!
				</Typography>
				<Form onSubmit={handleSubmit}>
					<Box
						sx={{
							display: 'flex',
							flexDirection: 'column',
							rowGap: '0.25rem',
						}}
					>
						<TextField
							label="Email"
							required
							variant="standard"
							error={!!errors.email}
							onChange={(event) => handleChange('email', event.target.value)}
						/>
						<TextField
							label="Senha"
							required
							type="password"
							variant="standard"
							onChange={(event) => handleChange('password', event.target.value)}
						/>
					</Box>
					<Box
						sx={{
							display: 'flex',
							flexDirection: 'column',
							rowGap: '0.25rem',
						}}
					>
						<Button type="submit" variant="outlined">
							Login
						</Button>
						<Button variant="text" onClick={() => push('/singIn')}>
							Criar uma conta
						</Button>
					</Box>
				</Form>
			</Box>
		</Box>
	);
}
