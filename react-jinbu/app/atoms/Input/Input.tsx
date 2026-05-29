'use client';

import "./Input.css";

interface InputProps {
    type: string;
    placeholder: string;
    value: string;
    onChange?:(e: React.ChangeEvent <HTMLInputElement>) => void;

}

/** Input estilizado reutilizable. Usado dentro de FormField para los formularios de la app. */
export default function Input({type, placeholder, value, onChange = () => {} }: InputProps){
    return (
        <input
            type={type}
            placeholder={placeholder}
            value={value}
            onChange={onChange}
            className="input-field"
        />
    );
}