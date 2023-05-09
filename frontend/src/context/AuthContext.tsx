import React, { createContext, useState, useContext, ReactNode } from 'react';

interface AuthContextData {
  token: string | null;
  login: (email: string, password: string) => Promise<boolean>;
  logout: () => void;
}

interface AuthProviderProps {
  children: ReactNode;
}

export const AuthContext = createContext<AuthContextData>({
	token: null,
	login: async () => false,
	// eslint-disable-next-line @typescript-eslint/no-empty-function
	logout: () => {},
});

export function AuthProvider({ children }: AuthProviderProps) {
	const [token, setToken] = useState<string | null>(null);

	const login = async (email: string, password: string) => {
		try {
			const newToken = Buffer.from(`${email}:${password}`).toString('base64');
			const response = await fetch(`${process.env.JUSTICE_FOR_ALL_API_URL}/login`, {
				method: 'POST',
				headers: {
					Authorization: `Basic ${newToken}`
				}
			});

			if (response.ok && response.status === 204) {
				setToken(newToken);
				return true;
			} else {
				return false;
			}
		} catch (err) {
			if (err instanceof Error) {
				console.error('Error to log in: ', err.message);
			}
			console.error(err);
			return false;
		}
	};

	const logout = () => {
		setToken(null);
	};

	return (
		<AuthContext.Provider value={{ token, login, logout }}>
			{children}
		</AuthContext.Provider>
	);
}

export const useAuth = () => useContext(AuthContext);
