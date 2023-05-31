import React from 'react';
import { SignupForm } from '@/components/molecules/SignupForm';
import { CenteredContainer } from '@/components/molecules/CenteredContainer';

export default function SignupPage() {
	return (
		<CenteredContainer title="Cadastre-se na nossa plataforma!">
			<SignupForm />
		</CenteredContainer>
	);
}
