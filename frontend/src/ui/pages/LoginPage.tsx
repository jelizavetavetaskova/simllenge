import InputField from "../components/shared/InputField.tsx";
import {useState, type SubmitEvent, type ChangeEvent} from "react";
import type {Login} from "../../types/app.ts";
import Button from "../components/shared/Button.tsx";
import {Link, useNavigate} from "react-router-dom";
import {useAuth} from "../../context/auth/AuthProvider.tsx";
import styles from "./LoginPage.module.css";
import LoginLayout from "../components/auth/LoginLayout.tsx";

const LoginPage = () => {
    const [loginData, setLoginData] = useState<Login>({
        email: "",
        password: ""
    });

    const [error, setError] = useState("");

    const navigate = useNavigate();

    const {signIn} = useAuth();

    const handleLogin = async (e: SubmitEvent<HTMLFormElement>) => {
        e.preventDefault();

        try {
            await signIn(loginData);
            navigate("/challenges");
        } catch (e) {
            (e instanceof Error) ? setError(e.message) : setError(String(e));
        }
    }

    const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
        setError("");
        const {name, value} = e.target;

        setLoginData((prev) => ({
            ...prev,
            [name]: value
        }))
    }

    return (
        <LoginLayout type="login" subheading="Log in to continue your challenges">
            <form onSubmit={handleLogin} className={styles.loginForm}>
                <InputField
                    id="email"
                    name="email"
                    label="Email: "
                    value={loginData.email}
                    onChange={handleChange}
                    inputType="email"
                />

                <InputField
                    id="password"
                    name="password"
                    label="Password: "
                    value={loginData.password}
                    onChange={handleChange}
                    inputType="password"
                />

                <Button type="submit" variant="primary">Login</Button>
            </form>
            <p className={styles.register}>Don't have an account? <Link to="/register" className={styles.link}>Create an account</Link></p>

            {error && <p>{error}</p>}
        </LoginLayout>
    )
}

export default LoginPage;