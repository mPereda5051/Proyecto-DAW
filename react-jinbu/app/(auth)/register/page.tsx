"use client";
import { useState } from "react";
import FormField from "@/app/molecule/FormField/FormField";
import Button from "@/app/atoms/Button/Button";
import "./register.css";
import { useRouter } from "next/navigation";
import { register } from "@/app/services/authService";

export default function Register() {
    const router = useRouter();

    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [loading, setLoading] = useState(false);


    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        handleRegister();
    };

    const handleRegister = async () => {
        if (!username || !email || !password || !confirmPassword) {
            alert("Por favor completa todos los campos");
            return;
        }

        if (password !== confirmPassword) {
            alert("Las contraseñas no coinciden");
            return;
        }

        setLoading(true);
        try {
            await register(username, email, password);
            alert("Registro exitoso. Ahora puedes iniciar sesión.");
            router.push("/login");
        } catch (error) {
            alert("Error al registrar el usuario. Inténtalo de nuevo.");
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="register-page">
            <div className="register-overlay"></div>
            <div className="register-logo">Jinbu</div>

            <div className="register-center">
                <div className="register-box">
                    <h1>Registrarse</h1>
                    <form onSubmit={handleSubmit}>

                        <FormField
                            label="Nombre de Usuario"
                            type="text"
                            placeholder="Tu nombre"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                        />
                        <FormField
                            label="Email"
                            type="email"
                            placeholder="Tu@email.com"
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
                        <FormField
                            label="Confirmar contraseña"
                            type="password"
                            placeholder="Repite tu contraseña"
                            value={confirmPassword}
                            onChange={(e) => setConfirmPassword(e.target.value)}
                        />
                        
                        <Button
                            label={loading ? "Registrando..." : "Crear cuenta"}
                            type="submit"
                            width="100%"
                            disabled={loading}
                        />
                    </form>

                    <p className="login-link" style={{ marginTop: '1rem', textAlign: 'center' }}>
                        ¿Ya tienes cuenta? <a href="/login" style={{ color: '#0070f3', textDecoration: 'none' }}>Inicia sesión</a>
                    </p>
                </div>
            </div>
        </div>
    );
}