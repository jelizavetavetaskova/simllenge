import {register} from "../../service/authService.ts";
import {type ChangeEvent, type SubmitEvent, useState} from "react";
import type {Register} from "../../types/app.ts";
import InputField from "../components/shared/InputField.tsx";
import Button from "../components/shared/Button.tsx";
import styles from "./RegisterPage.module.css";
import {useNavigate} from "react-router-dom";

const RegisterPage = () => {
    const [userData, setUserData] = useState<Register>({
        username: "",
        email: "",
        password: ""
    });
    const [confirmPassword, setConfirmPassword] = useState("");

    const [error, setError] = useState("");

    const navigate = useNavigate();

    const handleRegister = async (e: SubmitEvent<HTMLFormElement>) => {
        e.preventDefault();
        setError("");

        if (userData.password !== confirmPassword) {
            setError("Passwords do not match");
            return;
        }

        try {
            await register(userData);
            setUserData({
                username: "",
                email: "",
                password: ""
            });
            setConfirmPassword("");
            navigate("/login");
        } catch (e) {
            (e instanceof Error) ? setError(e. message) : setError(String(e));
        }
    }

    const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
        const {name, value} = e.target;

        setUserData((prev) => ({
            ...prev,
            [name]: value
        }));
    }

    return (
        <div className={styles.page}>
            <h1 className={styles.heading}>Register</h1>

            <form onSubmit={handleRegister} className={styles.registerForm}>
                <InputField
                    id="username"
                    name="username"
                    label="Username: "
                    value={userData.username}
                    onChange={handleChange}
                    inputType="text"
                />

                <InputField
                    id="email"
                    name="email"
                    label="Email: "
                    value={userData.email}
                    onChange={handleChange}
                    inputType="email"
                />

                <InputField
                    id="password"
                    name="password"
                    label="Password: "
                    value={userData.password}
                    onChange={handleChange}
                    inputType="password"
                />

                <InputField
                    id="confirmPassword"
                    label="Confirm password: "
                    value={confirmPassword}
                    onChange={(e) => setConfirmPassword(e.target.value)}
                    inputType="password"
                />

                <Button variant="primary" type="submit">Create an account</Button>
            </form>

            {error && <p>{error}</p>}
        </div>
    )
}

export default RegisterPage;