import axios from 'axios';
import React, { createContext, useState, useContext, ReactNode, useEffect } from 'react';

interface ILoggedUser {
	userId: number;
	type: string;
}

interface AuthContextData {
  token: string | null;
  login: (email: string, password: string) => Promise<boolean>;
  logout: () => void;
	isLogged: boolean
	loggedUser: ILoggedUser | null;
}

interface AuthProviderProps {
  children: ReactNode;
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
	const [isLogged, setIsLogged] = useState(false);
	const [loggedUser, setLoggedUser] = useState(null);

	const axiosInstance = axios.create({
		baseURL: process.env.JUSTICE_FOR_ALL_API_URL
	})

	useEffect(() => {
		setIsLogged(!!token);
	}, [token])

	const login = async (email: string, password: string) => {
		try {
			const newToken = Buffer.from(`${email}:${password}`).toString('base64');
			const response = await axiosInstance.post('/login', {}, {
				headers: {
					Authorization: `Basic ${newToken}`
				}
			});

			if (response.status === 200) {
				setToken(newToken);
				setLoggedUser(response.data)
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
	};

	return (
		<AuthContext.Provider value={{ token, login, logout, isLogged, loggedUser }}>
			{children}
		</AuthContext.Provider>
	);
}

export const useAuth = () => useContext(AuthContext);
