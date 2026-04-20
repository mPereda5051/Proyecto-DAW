'use client';

import React, { useEffect, useState } from 'react';
import Link from 'next/link';
import MenuIcon from '@mui/icons-material/Menu';
import CloseIcon from '@mui/icons-material/Close';
import AddButton from '../atoms/AddButtonComponent/AddButton';
import FormField from '../atoms/FormField/FormField';


export default function Navbar() {
  const [mounted, setMounted] = useState(false);
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  
  useEffect(() => {
    setMounted(true);
  }, []);

  const toggleMenu = () => {
    setIsMenuOpen(!isMenuOpen);
  };

  if (!mounted) {
    return <header className="menu"></header>;
  }

  return (
    <>
      <header className="menu">
        {/* Botón Menú y Logo */}
        <div className="menu-logo">
          <MenuIcon onClick={toggleMenu} style={{ cursor: 'pointer' }} />
          <Link href="/" className="logo-link">
            <p>Jinbu</p>
          </Link>
        </div>

        {/* Buscador */}
        <div className="navbar-search-wrapper">
          <FormField radio='20px' />
        </div>

        {/* perfil */}
        <div className="menu-actions">
          <AddButton />
          <div className="user-profile">
            <p style={{ margin: 0, fontSize: '14px' }}>Perfil</p>
            <div style={{ width: 32, height: 32, borderRadius: '50%', backgroundColor: '#555' }}></div>
          </div>
        </div>
      </header>

      {/* Sidebar */}
      <div 
        className={`sidebar-overlay ${isMenuOpen ? 'open' : ''}`} 
        onClick={toggleMenu}
      ></div>

      {/* Contenedor del menú */}
      <nav className={`sidebar ${isMenuOpen ? 'open' : ''}`}>
        <div className="sidebar-header">
          <h2>Menú</h2>
          <CloseIcon onClick={toggleMenu} style={{ cursor: 'pointer' }} />
        </div>

        <ul className="sidebar-links">
          <li><Link href="/" onClick={toggleMenu}>Inicio</Link></li>
          <li><Link href="/cuenta" onClick={toggleMenu}>Cuenta</Link></li>
          <li><Link href="/soporte" onClick={toggleMenu}>Soporte</Link></li>
          <hr className="sidebar-divider" />
          <li><Link href="/cookies" onClick={toggleMenu}>Política de Cookies</Link></li>
          <li><Link href="/privacidad" onClick={toggleMenu}>Política de Privacidad</Link></li>
        </ul>
      </nav>
    </>
  );
}
