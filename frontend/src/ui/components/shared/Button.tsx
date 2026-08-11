import type {ReactNode} from "react";
import styles from "./Button.module.css";

interface ButtonProps {
    variant: "primary"|"secondary"
    onClick?: () => void;
    children: ReactNode;
    type?: "button"|"submit";
}

const Button = ({variant, onClick, children, type = "button"}: ButtonProps) => {
    return (
        <button onClick={onClick} className={variant === "primary" ? styles.primary : styles.secondary} type={type}>
            {children}
        </button>
    )
}

export default Button;