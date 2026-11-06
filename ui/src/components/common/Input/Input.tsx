import React, { InputHTMLAttributes } from 'react';
import styles from './Input.module.css';

interface InputProps extends InputHTMLAttributes<HTMLInputElement> {
    label: string;
}

export const Input = ({ label, ...props }: InputProps) => {
    return (
        <div className={styles.inputWrapper}>
            <label className={styles.label}>{label}</label>
            <input className={styles.inputField} {...props} />
        </div>
    );
};