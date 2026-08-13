import type {Login, Register} from "../types/app.ts";
import type {User} from "../types/database.ts";

export const register = async (userData: Register) => {
    const res = await fetch("/api/auth/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(userData),
        credentials: "include"
    });

    if (!res.ok) throw Error(await res.text());

    const data: User = await res.json();
    return data;
}

export const login = async (loginData: Login) => {
    const res = await fetch("/api/auth/login", {
        method: "POST",
        body: JSON.stringify(loginData),
        credentials: "include",
        headers: {
            "Content-Type": "application/json"
        }
    });

    if (!res.ok) throw Error(await res.text());
}