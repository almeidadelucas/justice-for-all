import React from 'react';
import { LoginForm } from '@/components/molecules/LoginForm';
import { CenteredContainer } from '@/components/molecules/CenteredContainer';

export default function LoginPage() {
	return (
		<CenteredContainer title="Olá! Boas vindas!">
			<LoginForm />
		</CenteredContainer>
	);
}
