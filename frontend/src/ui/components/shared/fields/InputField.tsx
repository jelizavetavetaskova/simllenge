import styles from "./Field.module.css";
import type {ChangeEvent} from "react";

interface InputFieldProps {
    id: string;
    name?: string;
    label: string;
    inputType: string;
    placeholder?: string;
    value: string|number;
    onChange: (e: ChangeEvent<HTMLInputElement>) => void;
}

const InputField = ({id, name, label, inputType, placeholder, value, onChange}: InputFieldProps) => {
    return (
        <div className={styles.field}>
            <label htmlFor={id} className={styles.label}>{label}</label>
            <input
                id={id}
                name={name}
                type={inputType}
                placeholder={placeholder}
                value={value}
                onChange={onChange}
                className={styles.input}
            />
        </div>
    )
}

export default InputField;