'use client';

import { useEffect, useState } from 'react';
import { getCurrentUsername } from '@/app/services/authService';
import './cuenta.css';

export default function Cuenta() {
  const [username, setUsername] = useState<string | null>(null);

  useEffect(() => {
    setUsername(getCurrentUsername());
  }, []);

  return (
    <main className="cuenta-container">
      <h1>Mi cuenta</h1>
      <div className="cuenta-card">
        <h2>Información de la cuenta</h2>
        <div className="cuenta-field">
          <span className="cuenta-field-label">Usuario</span>
          <span className="cuenta-field-value">@{username ?? '...'}</span>
        </div>
        <div className="cuenta-field">
          <span className="cuenta-field-label">Plan</span>
          <span className="cuenta-field-value">Gratuito</span>
        </div>
      </div>
    </main>
  );
}
