import InputField from "../components/shared/InputField.tsx";
import {useState, type SubmitEvent, type ChangeEvent} from "react";
import type {Login} from "../../types/app.ts";
import Button from "../components/shared/Button.tsx";
import {Link, useNavigate} from "react-router-dom";
import {useAuth} from "../../auth/AuthProvider.tsx";

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
        <div>
            <h1>Login</h1>

            <form onSubmit={handleLogin}>
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
            <p>Don't have an account? <Link to="/register">Create an account</Link></p>

            {error && <p>{error}</p>}
        </div>
    )
}

export default LoginPage;