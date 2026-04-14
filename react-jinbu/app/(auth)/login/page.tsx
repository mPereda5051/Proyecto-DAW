"use client";
import {useState} from "react";
import "./login.css";
import FormField from "@/app/atoms/FormField/FormField";

export default function Login(){

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleLogin = () => {
        if (email === "" || password ===""){
            alert("Por favor completa todos los campos")
            return;
        }
        console.log("Login exitoso");
        console.log("Email:", email);
        console.log("Password", password);

    };
    return (
        <div className= "login-container">
            <div className = "login-box">
                <h1>Iniciar Sesión</h1>
                <FormField radio="0px" />
                
                <input
                    type="password"
                    placeholder="Contraseña"
                    className="login-input"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
                <button className="login-button" onClick={handleLogin}>
                    Iniciar Sesión
                </button>
            </div>

            
        </div>
    );
}