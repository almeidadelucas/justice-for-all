import React from 'react';
import '@/styles/globals.css';
import type { AppProps } from 'next/app';
import { AuthProvider } from '../context/AuthContext';
import { Layout } from '@/components/templates/Layout';

export default function App({ Component, pageProps }: AppProps) {
	return (
		<AuthProvider>
			<Layout>
				<Component {...pageProps} />
			</Layout>
		</AuthProvider>
	);
}
