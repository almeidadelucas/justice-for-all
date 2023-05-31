import { AuthContext } from '@/context/AuthContext';
import React, { useRouter } from 'next/router';
import { useContext, useEffect } from 'react';

export function withAuth(WrappedComponent: React.ComponentType) {
	const WithAuth: React.FC = (props) => {
		const router = useRouter();
		const { token } = useContext(AuthContext);

		useEffect(() => {
			if (!token) {
				router.push('/login');
			}
		}, [token, router]);


		if (!token) {
			return null;
		}
	
		return <WrappedComponent {...props} />;
	};

	return WithAuth;
}
