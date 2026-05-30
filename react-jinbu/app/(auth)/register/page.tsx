"use client";
import { useState } from "react";
import FormField from "@/app/molecule/FormField/FormField";
import Button from "@/app/atoms/Button/Button";
import "./register.css";
import { useRouter } from "next/navigation";
import { register } from "@/app/services/authService";
import { useSnackbar } from "notistack";

/** Página de registro. Gestiona el formulario de creación de cuenta y redirige al login si tiene éxito. */
export default function Register() {
    const { enqueueSnackbar } = useSnackbar();
    const router = useRouter();

    const [name, setName] = useState("");
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [loading, setLoading] = useState(false);

    /** Previene el comportamiento por defecto del formulario y llama a handleRegister. */
    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        handleRegister();
    };

    /** Valida los campos, comprueba que las contraseñas coincidan y llama al servicio de registro. */
    const handleRegister = async () => {
        if (!name || !username || !email || !password || !confirmPassword) {
            enqueueSnackbar("Por favor completa todos los campos", { variant: 'warning' });
            return;
        }

        if (password !== confirmPassword) {
            enqueueSnackbar("Las contraseñas no coinciden", { variant: 'error' });
            return;
        }

        if (password.length < 6) {
            enqueueSnackbar("La contraseña debe tener al menos 6 caracteres", { variant: 'warning' });
            return;
        }

        setLoading(true);
        try {
            await register(name, username, email, password);
            enqueueSnackbar("Registro exitoso. ¡Bienvenido!", { variant: 'success' });
            router.push("/login");
        } catch (error) {
            enqueueSnackbar("Error al registrar el usuario. Inténtalo de nuevo.", { variant: 'error' });
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
                            label="Nombre"
                            type="text"
                            placeholder="Tu nombre completo"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                        />
                        <FormField
                            label="Nombre de Usuario"
                            type="text"
                            placeholder="Tu nombre de usuario"
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

                    <p className="login-link">
                        ¿Ya tienes cuenta? <a href="/login">Inicia sesión</a>
                    </p>
                </div>
            </div>
        </div>
    );
}