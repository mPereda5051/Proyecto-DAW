'use client';

import "./Button.css"; 
interface ButtonProps{
    label: string;
    onClick: () => void;
}

export default function Button ({label, onClick}: ButtonProps){
    return(
        <button className="btn" onClick={onClick} >
            {label}
        </button>
    )
}