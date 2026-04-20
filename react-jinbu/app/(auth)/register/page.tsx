"use client";
import {useState} from "react";
import FormField from "@/app/molecule/FormField/FormField";
import Button from "@/app/atoms/Button/Button";
import "./register.css";


export default function Register(){

    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");

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

    return (
        <div className="register-container">
            <div className="register-box">
                <h1>Registrarse</h1>

                <FormField
                    label= "Nombre de Usuario"
                    type="text"
                    placeholder="Tu nombre"
                    value={username}
                    onChange={(e) => setEmail(e.target.value)}
                />
                <FormField
                    label= "Email"
                    type="email"
                    placeholder="Tu@email.com"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />
                <FormField
                    label= "Contraseña"
                    type="password"
                    placeholder="Contraseña"
                    value={password}
                    onChange={(e) => setEmail(e.target.value)}
                />
                <FormField
                    label= "Confirmar contraseña"
                    type="password"
                    placeholder="Repite tu contraseña"
                    value={confirmPassword}
                    onChange={(e) => setEmail(e.target.value)}
                />
                <Button
                    label="Crear cuenta"
                    onClick={handleRegister}
                />
            </div>
        </div>
        
    );
}