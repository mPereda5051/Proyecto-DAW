'use client';

import "./Button.css"; 
interface ButtonProps{
    label: string;
    width: string;
    disabled?: boolean;
    onClick: () => void;
}

export default function Button ({label, onClick, width, disabled = false}: ButtonProps){
    return(
        <button className="btn" onClick={onClick} style={{width: width}} disabled={disabled} >
            {label}
        </button>
    )
}