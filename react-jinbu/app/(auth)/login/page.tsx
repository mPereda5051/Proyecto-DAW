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
    const [loading, setLoading] = useState(false);

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        handleLogin();
    };

    const handleLogin = async () => {
        if (username === "" || password === "") {
            alert("Por favor completa todos los campos");
            return;
        }

        setLoading(true);
        try {
            await login(username, password);
            router.push("/");
        } catch (error) {
            alert("Usuario o contraseña incorrectos");
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="login-page">
            <div className="login-overlay"></div>
            
            <div className="login-logo">Jinbu</div>
            
            <div className="login-center">
                        <div className="login-box">
                            <h1>Iniciar Sesión</h1>
                            <form onSubmit={handleSubmit}>
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
                                label={loading ? "Cargando..." : "Iniciar Sesión"}
                                type="submit"
                                width="100%"
                                disabled={loading}
                            />
                            </form>
                            <p className="register-link">
                                ¿No tienes cuenta? <a href="/register">Regístrate</a>
                            </p>
                        </div>
                </div>
        </div>
    );
}
