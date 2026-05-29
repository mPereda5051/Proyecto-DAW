'use client';

import "./Button.css"; 
interface ButtonProps{
    label: string;
    width: string;
    disabled?: boolean;
    onClick?: () => void;
    type?: "button" | "submit" | "reset";
}

/** Botón reutilizable con soporte para ancho, estado deshabilitado y tipo (button/submit/reset). */
export default function Button ({label, onClick, width, disabled = false, type = "button"}: ButtonProps){
    return(
        <button className="btn" type={type} onClick={onClick} style={{width: width}} disabled={disabled} >
            {label}
        </button>
    )
}