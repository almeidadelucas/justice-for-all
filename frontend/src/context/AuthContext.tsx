import axios from 'axios';
import { useRouter } from 'next/router';
import React, { createContext, useState, useContext, ReactNode, useEffect } from 'react';

interface ILoggedUser {
	userId: number;
	type: string;
}

interface AuthContextData {
  token: string | null;
  login: (input: ILogin) => Promise<boolean>;
  logout: () => void;
	isLogged: boolean
	loggedUser: ILoggedUser | null;
}

interface AuthProviderProps {
  children: ReactNode;
}

interface ILogin {
	email?: string;
	password?: string
	token?: string;
}

export const AuthContext = createContext<AuthContextData>({
	token: null,
	login: async () => false,
	// eslint-disable-next-line @typescript-eslint/no-empty-function
	logout: () => {},
	isLogged: false,
	loggedUser: null
});

export function AuthProvider({ children }: AuthProviderProps) {
	const [token, setToken] = useState<string | null>(null);
	const { push } = useRouter();

	const [isLogged, setIsLogged] = useState(false);
	const [loggedUser, setLoggedUser] = useState(null);

	const axiosInstance = axios.create({
		baseURL: process.env.JUSTICE_FOR_ALL_API_URL
	})

	useEffect(() => {
		const recoveredToken = window.sessionStorage.getItem('token');
		if (recoveredToken !== null) {
			login({ token: recoveredToken })
			push('/');
		}
	}, [])

	const login = async ({ email, password, token }: ILogin) => {
		try {
			const newToken = token || Buffer.from(`${email}:${password}`).toString('base64');
			const response = await axiosInstance.post('/login', {}, {
				headers: {
					Authorization: `Basic ${newToken}`
				}
			});

			if (response.status === 200) {
				window.sessionStorage.setItem('token', newToken);
				setToken(newToken);
				setLoggedUser(response.data)
				setIsLogged(true);
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
		setLoggedUser(null)
		setToken(null);
		setIsLogged(false);
		window.sessionStorage.removeItem('token');
	};

	return (
		<AuthContext.Provider value={{ token, login, logout, isLogged, loggedUser }}>
			{children}
		</AuthContext.Provider>
	);
}

export const useAuth = () => useContext(AuthContext);

