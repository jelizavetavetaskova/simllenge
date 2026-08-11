import styles from "./Field.module.css";
import type {ChangeEvent, ReactNode} from "react";

interface SelectFieldProps {
    id: string;
    name?: string;
    label: string;
    value: string|number;
    onChange: (e: ChangeEvent<HTMLSelectElement>) => void;
    children: ReactNode;
}

const SelectField = ({id, name, label, value, onChange, children}: SelectFieldProps) => {
    return (
        <div className={styles.field}>
            <label htmlFor={id} className={styles.label}>{label}</label>
            <select
                name={name}
                id={id}
                value={value}
                onChange={onChange}
                className={styles.input}
            >
                {children}
            </select>
        </div>
    )
}

export default SelectField;