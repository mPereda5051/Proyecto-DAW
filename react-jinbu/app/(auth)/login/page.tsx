"use client";
import { useState } from "react";
import FormField from "@/app/molecule/FormField/FormField";
import Button from "@/app/atoms/Button/Button";
import "./login.css"
import { useRouter } from "next/navigation";
import { login } from "@/app/services/authService";
import { useSnackbar } from "notistack";

/** Página de inicio de sesión. Gestiona el formulario y redirige al home si el login es correcto. */
export default function Login() {

    const { enqueueSnackbar } = useSnackbar();
    const router = useRouter();
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);

    /** Previene el comportamiento por defecto del formulario y llama a handleLogin. */
    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        handleLogin();
    };

    /** Valida los campos, llama al servicio de login y redirige al home. */
    const handleLogin = async () => {
        if (username === "" || password === "") {
            enqueueSnackbar("Por favor completa todos los campos", { variant: 'warning' });
            return;
        }

        setLoading(true);
        try {
            await login(username, password);
            enqueueSnackbar("¡Bienvenido de nuevo!", { variant: 'success' });
            router.push("/");
        } catch (error) {
            enqueueSnackbar("Usuario o contraseña incorrectos", { variant: 'error' });
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
