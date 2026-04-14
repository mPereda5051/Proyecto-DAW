"use client";
import {useState} from "react";
import "./register.css";


export default function Register(){


    const handleRegister = () => {

        if( username ==="" || email === "" || password ==="" || confirmPassword === "" ){
            alert("por favor completa todos los campos");
            return;
        }

        if(password !== confirmPassword){
            alert("Las contraseñas no coinciden");
            return;
        }

        console.log("Registro exitoso")
        console.log("Username:", username)
        console.log("Email:", email)
    };

    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");

    return (
        <div className="register-container">
            <div className="register-box">
                <h1>Registrarse</h1>

                <input type="text" 
                placeholder="Nombre de usuario"
                className="register-input"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                />

                <input type="email" 
                placeholder="email"
                className="register-input"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                />

                <input type="password" 
                placeholder="contraseña"
                className="register-input"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                />

                <input type="password"
                placeholder="Escribe otra vez tu contraseña"
                className="register-input"
                value={confirmPassword}
                onChange={(e) => setConfirmPassword(e.target.value)}
                />

                <button className="register-button" onClick={handleRegister}>
                    Crear Cuenta
                </button>
            </div>
        </div>
        
    );
}