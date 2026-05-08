'use client';

import React from 'react';
import './opciones.css';
import Button from '../../atoms/Button/Button';
import Input from '../../atoms/Input/Input';
import AvatarImage from '../../atoms/AvatarImage/AvatarImage';

export default function OpcionesPage() {
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
                    <div className="form-group">
                        <label>Nombre</label>
                        <Input type="text" placeholder="Juan Pérez" value="Juan Pérez" />
                    </div>
                    <div className="form-group">
                        <label>Usuario</label>
                        <Input type="text" placeholder="juan123" value="juan123" />
                    </div>
                    <div className="form-group">
                        <label>Correo</label>
                        <Input type="email" placeholder="juanperez@email.com" value="juanperez@email.com" />
                    </div>
                    <div className="form-group">
                        <label>Teléfono</label>
                        <Input type="text" placeholder="+34 600 000 000" value="+34 600 000 000" />
                    </div>
                    <Button label="Guardar Cambios" onClick={() => {}} />
                </section>

                <section className="opciones-card">
                    <h2>Cambiar contraseña</h2>
                    <div className="form-group">
                        <label>Contraseña actual</label>
                        <Input type="password" placeholder="Contraseña actual" value="" />
                    </div>
                    <div className="form-group">
                        <label>Nueva contraseña</label>
                        <Input type="password" placeholder="Nueva contraseña" value="" />
                    </div>
                    <div className="form-group">
                        <label>Confirmar nueva contraseña</label>
                        <Input type="password" placeholder="Confirmar nueva contraseña" value="" />
                    </div>
                    <Button label="Cambiar Contraseña" onClick={() => {}} />
                </section>
            </div>
        </div>
    );
}
