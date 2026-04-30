"use client";
import { useState } from "react";
import FormField from "@/app/molecule/FormField/FormField";
import Button from "@/app/atoms/Button/Button";
import "./login.css"
import { useRouter } from "next/navigation";
import { login } from "@/app/services/authService";

export default function Login() {

    const router = useRouter();
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const handleLogin = async () => {
        if (username === "" || password === "") {
            alert("Por favor completa todos los campos");
            return;
        }

        try {
            await login(username, password);
            console.log("Login exitoso");
            router.push("/");
        } catch (error) {
            alert("Usuario o contraseña incorrectos");
        }
    };

    return (
        <div className="login-page">
            <div className="login-overlay"></div>
            
            <div className="login-logo">Jinbu</div>
            
            <div className="login-center">
                        <div className="login-box">
                            <h1>Iniciar Sesión</h1>
                            <FormField
                                label="Usuario"
                                type="text"
                                placeholder="Tu nombre de usuario"
                                value={username}
                                onChange={(e) => setUsername(e.target.value)}
                            />
                            <FormField
                                label="Contraseña"
                                type="password"
                                placeholder="Contraseña"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                            />

                            <Button
                                label="Iniciar Sesión" onClick={handleLogin}
                            />
                            <p className="register-link">
                                ¿No tienes cuenta? <a href="/register">Regístrate</a>
                            </p>
                        </div>
                </div>
        </div>
    );
}
