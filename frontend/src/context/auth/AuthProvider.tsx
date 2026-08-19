import {createContext, type ReactNode, useContext, useEffect, useState} from "react";
import type {User} from "../../types/database.ts";
import type {Login} from "../../types/app.ts";
import {login, logout} from "../../service/authService.ts";

interface AuthContextType {
    user: User | null;
    loading: boolean;
    signIn: (loginData: Login) => Promise<void>;
    signOut: () => void;
}

const AuthContext = createContext<AuthContextType|undefined>(undefined);

export const useAuth = () => {
    const context = useContext(AuthContext);

    if (!context) {
        throw Error("useAuth must be used within AuthProvider");
    }

    return context;
}

const AuthProvider = ({children}: {children: ReactNode}) => {
    const [user, setUser] = useState<User|null>(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const checkAuth = async () => {
            try {
                const res = await fetch("/api/auth/me", {
                    credentials: "include"
                });

                if (res.ok) {
                    const data: User = await res.json();
                    setUser(data);
                } else {
                    setUser(null);
                }
            } finally {
                setLoading(false);
            }
        }

        checkAuth();
    }, []);

    const signIn = async (loginData: Login) => {
        const user: User = await login(loginData);
        setUser(user);
    }

    const signOut = async () => {
        await logout();
        setUser(null);
    }

    return (
        <AuthContext.Provider value={{user, loading, signIn, signOut}}>
            {children}
        </AuthContext.Provider>
    )
}

export default AuthProvider;