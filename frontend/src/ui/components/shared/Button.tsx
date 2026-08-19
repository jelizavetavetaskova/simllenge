import type {ReactNode} from "react";
import styles from "./Button.module.css";

interface ButtonProps {
    variant: "primary"|"secondary"|"add"
    onClick?: () => void;
    children: ReactNode;
    type?: "button"|"submit";
    className?: string
}

const Button = ({variant, onClick, children, type = "button", className}: ButtonProps) => {

    return (
        <button onClick={onClick} className={`${styles[variant]} ${className ?? ""}`} type={type}>
            {children}
        </button>
    )
}

export default Button;