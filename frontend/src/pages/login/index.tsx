import React, { FormEvent, useState, useContext } from 'react';
import styled from '@emotion/styled';
import { TextField, Box, Typography, Button } from '@mui/material';
import { useRouter } from 'next/router';
import { AuthContext } from '../../context/AuthContext';

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
	/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

export default function Login() {
	const { login } = useContext(AuthContext);
	const { push } = useRouter();

	const [user, setUser] = useState<IUser>({
		email: '',
		password: '',
	});
	const [errors, setErrors] = useState<IUser>({
		email: '',
		password: '',
	});

	const handleChange = (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
		const fieldName =  event.target.name;
		const fieldValue = event.target.value;
		setUser(current => ({
			...current,
			[fieldName]: fieldValue
		}));
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

	const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
		event.preventDefault();
		if (validate()) {
			const loggedIn = await login(user.email, user.password);
			if (!loggedIn) {
				alert('Usuário ou senha incorretos');
			} else {
				push('/');
			}
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
							name="email"
							required
							variant="standard"
							error={!!errors.email}
							helperText={errors.email}
							onChange={handleChange}
						/>
						<TextField
							label="Senha"
							name="password"
							required
							type="password"
							variant="standard"
							onChange={handleChange}
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
						<Button variant="text" onClick={() => push('/signup')}>
							Criar uma conta
						</Button>
					</Box>
				</Form>
			</Box>
		</Box>
	);
}
