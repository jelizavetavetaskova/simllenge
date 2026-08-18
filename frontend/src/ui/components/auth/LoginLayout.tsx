import type {ReactNode} from "react";
import styles from "./LoginLayout.module.css";

interface LoginLayoutProps {
    type: "login"|"register";
    subheading: string;
    children: ReactNode;
}

const LoginLayout = ({type, subheading, children}: LoginLayoutProps) => {
    return (
        <div className={styles.layout}>
            <div className={styles.page}>
                <div className={styles.content}>
                    <h1 className={styles.heading}>{type === "login" ? "Welcome back" : "Create an account"}</h1>
                    <p className={styles.subheading}>{subheading}</p>

                    {children}
                </div>
            </div>
        </div>
    )
}

export default LoginLayout;