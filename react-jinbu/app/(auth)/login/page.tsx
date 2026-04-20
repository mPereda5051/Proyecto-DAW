"use client";
import {useState} from "react";
import FormField from "@/app/molecule/FormField/FormField";
import Button from "@/app/atoms/Button/Button";
import "./login.css"
import { useRouter } from "next/navigation";

export default function Login(){

    const router = useRouter();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleLogin = () => {
        if (email === "" || password ===""){
            alert("Por favor completa todos los campos")
            return;
        }
        console.log("Login exitoso");
        router.push("/")

    };
    return (
        <div className= "login-container">
            <div className = "login-box">
                <h1>Iniciar Sesión</h1>
                <FormField
                    label="Email"
                    type="email"
                    placeholder="tu@email.com"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />
                <FormField
                    label="Contraseña"
                    type="password"
                    placeholder="Contraseña"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />

                <Button
                    label= "Iniciar Sesión" onClick={handleLogin}
                />
                <p className="register-link">
                     ¿No tienes cuenta? <a href="/register">Regístrate</a>
                </p>
                
                
            </div>

            
        </div>
    );
}