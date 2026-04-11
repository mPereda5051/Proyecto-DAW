"use client";
import {useState} from "react";
import './login.css'

export default function Login(){

    const [email, setEmail] = useState("");

    return (
        <div className= "login-container">
            <div className = "login-box">
                <h1>Iniciar Sesión</h1>
                <input 
                    type= "email"
                    placeholder = "Email"
                    className="login-input"
                />
                <input
                    type="password"
                    placeholder="Contraseña"
                    className="login-input"
                />
            </div>
            
        </div>
    );
}