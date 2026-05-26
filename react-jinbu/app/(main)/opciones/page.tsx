'use client';

import React, { useState, useEffect } from 'react';
import './opciones.css';
import Button from '../../atoms/Button/Button';
import Input from '../../atoms/Input/Input';
import AvatarImage from '../../atoms/AvatarImage/AvatarImage';
import { getCurrentUser, updateProfile, changePassword } from '../../services/userService';

export default function OpcionesPage() {
    const [name, setName] = useState('');
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [loading, setLoading] = useState(true);
    const [updatingProfile, setUpdatingProfile] = useState(false);
    const [message, setMessage] = useState({ text: '', type: '' });

    const [currentPassword, setCurrentPassword] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [confirmNewPassword, setConfirmNewPassword] = useState('');
    const [changingPassword, setChangingPassword] = useState(false);
    const [passwordMessage, setPasswordMessage] = useState({ text: '', type: '' });

    useEffect(() => {
        const fetchUserData = async () => {
            try {
                const user = await getCurrentUser();
                setName(user.name || '');
                setUsername(user.username || '');
                setEmail(user.email || '');
            } catch (error) {
                setMessage({ text: 'Error al cargar los datos del usuario', type: 'error' });
            } finally {
                setLoading(false);
            }
        };

        fetchUserData();
    }, []);

    const handleUpdateProfile = async () => {
        setUpdatingProfile(true);
        setMessage({ text: '', type: '' });
        try {
            await updateProfile({ name, username, email });
            setMessage({ text: 'Perfil actualizado correctamente', type: 'success' });
        } catch (error) {
            setMessage({ text: 'Error al actualizar el perfil', type: 'error' });
        } finally {
            setUpdatingProfile(false);
        }
    };

    const handleChangePassword = async () => {
        if (newPassword !== confirmNewPassword) {
            setPasswordMessage({ text: 'Las contraseñas no coinciden', type: 'error' });
            return;
        }

        if (newPassword.length < 6) {
            setPasswordMessage({ text: 'La nueva contraseña debe tener al menos 6 caracteres', type: 'error' });
            return;
        }

        setChangingPassword(true);
        setPasswordMessage({ text: '', type: '' });
        try {
            await changePassword({ currentPassword, newPassword });
            setPasswordMessage({ text: 'Contraseña cambiada correctamente', type: 'success' });
            setCurrentPassword('');
            setNewPassword('');
            setConfirmNewPassword('');
        } catch (error) {
            setPasswordMessage({ text: 'Error al cambiar la contraseña. Verifica tu contraseña actual.', type: 'error' });
        } finally {
            setChangingPassword(false);
        }
    };

    if (loading) {
        return <div className="opciones-page">Cargando...</div>;
    }

    return (
        <div className="opciones-page">
            <header className="opciones-header">
                <h1>Opciones</h1>
                <div className="profile-photo-container">
                    <AvatarImage src="/images/user.jpg" alt="Foto de perfil" />
                </div>
            </header>

            <div className="opciones-sections">
                <section className="opciones-card">
                    <h2>Información de la cuenta</h2>
                    {message.text && (
                        <div className={`alert ${message.type}`}>
                            {message.text}
                        </div>
                    )}
                    <div className="form-group">
                        <label>Nombre</label>
                        <Input 
                            type="text" 
                            placeholder="Tu nombre" 
                            value={name} 
                            onChange={(e) => setName(e.target.value)} 
                        />
                    </div>
                    <div className="form-group">
                        <label>Usuario</label>
                        <Input 
                            type="text" 
                            placeholder="Nombre de usuario" 
                            value={username} 
                            onChange={(e) => setUsername(e.target.value)} 
                        />
                    </div>
                    <div className="form-group">
                        <label>Correo</label>
                        <Input 
                            type="email" 
                            placeholder="tu@email.com" 
                            value={email} 
                            onChange={(e) => setEmail(e.target.value)} 
                        />
                    </div>
                    <Button 
                        label={updatingProfile ? "Guardando..." : "Guardar Cambios"} 
                        onClick={handleUpdateProfile} 
                        width='100%' 
                        disabled={updatingProfile}
                    />
                </section>

                <section className="opciones-card">
                    <h2>Cambiar contraseña</h2>
                    {passwordMessage.text && (
                        <div className={`alert ${passwordMessage.type}`}>
                            {passwordMessage.text}
                        </div>
                    )}
                    <div className="form-group">
                        <label>Contraseña actual</label>
                        <Input 
                            type="password" 
                            placeholder="Contraseña actual" 
                            value={currentPassword} 
                            onChange={(e) => setCurrentPassword(e.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label>Nueva contraseña</label>
                        <Input 
                            type="password" 
                            placeholder="Nueva contraseña" 
                            value={newPassword} 
                            onChange={(e) => setNewPassword(e.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label>Confirmar nueva contraseña</label>
                        <Input 
                            type="password" 
                            placeholder="Confirmar nueva contraseña" 
                            value={confirmNewPassword} 
                            onChange={(e) => setConfirmNewPassword(e.target.value)}
                        />
                    </div>
                    <Button 
                        label={changingPassword ? "Cambiando..." : "Cambiar Contraseña"} 
                        onClick={handleChangePassword} 
                        width='100%' 
                        disabled={changingPassword}
                    />
                </section>
            </div>
        </div>
    );
}
