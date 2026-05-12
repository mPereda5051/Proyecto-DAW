'use client';

import "./Button.css"; 
interface ButtonProps{
    label: string;
    width: string;
    onClick: () => void;
}

export default function Button ({label, onClick, width}: ButtonProps){
    return(
        <button className="btn" onClick={onClick} style={{width: width}}>
            {label}
        </button>
    )
}