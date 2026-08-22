import type {ReactNode} from "react";
import styles from "./Button.module.css";

interface ButtonProps {
    variant: "primary"|"secondary"|"add"|"add_secondary"|"level";
    onClick?: () => void;
    children: ReactNode;
    type?: "button"|"submit";
    className?: string;
    disabled?: boolean
}

const Button = ({variant, onClick, children, type = "button", className, disabled = false}: ButtonProps) => {

    return (
        <button
            onClick={onClick}
            className={`${styles[variant]} ${disabled ? styles.disabled : ""} ${className ?? ""}`}
            type={type}
            disabled={disabled}
        >
            {children}
        </button>
    )
}

export default Button;