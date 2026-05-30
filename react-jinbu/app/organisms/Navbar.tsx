"use client";

import React, { useEffect, useState } from "react";
import Link from "next/link";
import MenuIcon from "@mui/icons-material/Menu";
import CloseIcon from "@mui/icons-material/Close";
import AddButton from "../atoms/AddButtonComponent/AddButton";
import SearchInput from "../atoms/SearchInput/SearchInput";
import { getCurrentUsername, logout } from "@/app/services/authService";
// Añadimos la importación de la foto de perfil
import { getProfilePictureUrl } from "@/app/services/userService";
import { useRouter } from "next/navigation";

export default function Navbar() {
  const [mounted, setMounted] = useState(false);
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const [username, setUsername] = useState<string | null>(null);
  const router = useRouter();
  const [isProfileMenuOpen, setIsProfileMenuOpen] = useState(false);
  
  // Añadimos el estado para la URL del avatar
  const [avatarUrl, setAvatarUrl] = useState<string>("/images/user.jpg");

  useEffect(() => {
    let isMounted = true;
    setMounted(true);
    
    const currentUser = getCurrentUsername();
    setUsername(currentUser);

    // Función asíncrona para cargar el avatar
    const loadAvatar = async () => {
      if (currentUser) {
        try {
          const url = await getProfilePictureUrl(currentUser);
          if (isMounted && url && typeof url === 'string' && url.startsWith('http')) {
            setAvatarUrl(url);
          }
        } catch (error) {
          // Si falla, se queda con /images/user.jpg por defecto
        }
      }
    };

    loadAvatar();

    return () => {
      isMounted = false;
    };
  }, []);

  const toggleMenu = () => {
    setIsMenuOpen(!isMenuOpen);
  };

  const handleLogout = () => {
    logout();
    setUsername(null);
    router.push("/login");
  };

  if (!mounted) {
    return <header className="menu"></header>;
  }

  return (
    <>
      <header className="menu">
        {/* Botón Menú y Logo */}
        <div className="menu-logo">
          <MenuIcon onClick={toggleMenu} style={{ cursor: "pointer" }} />
          <Link href="/" className="logo-link">
            <p>Jinbu</p>
          </Link>
        </div>

        {/* Buscador */}
        <div className="navbar-search-wrapper">
          <SearchInput radio="20px" enableSearch={true} />
        </div>

        {/* perfil */}
        <div className="menu-actions">
          <AddButton />

          <div className="user-profile-wrapper">
            <div
              className="user-profile"
              onClick={() => setIsProfileMenuOpen(!isProfileMenuOpen)}
            >
              <p>Perfil</p>
              {/* Actualizamos la imagen para que use la variable avatarUrl */}
              <img src={avatarUrl} alt="Perfil" className="user-avatar" />
            </div>

            {isProfileMenuOpen && (
              <div className="profile-dropdown">
                <p>@{username}</p>
                <hr />
                <Link
                  href={`/profile/${username}`}
                  onClick={() => setIsProfileMenuOpen(false)}
                >
                  Ver perfil
                </Link>
                <Link
                  href="/opciones"
                  onClick={() => setIsProfileMenuOpen(false)}
                >
                  Opciones
                </Link>
                <hr />
                <button onClick={handleLogout}>Cerrar sesión</button>
              </div>
            )}
          </div>
        </div>
      </header>

      {/* Sidebar */}
      <div
        className={`sidebar-overlay ${isMenuOpen ? "open" : ""}`}
        onClick={toggleMenu}
      ></div>

      {/* Contenedor del menú */}
      <nav className={`sidebar ${isMenuOpen ? "open" : ""}`}>
        <div className="sidebar-header">
          <h2>Menú</h2>
          <CloseIcon onClick={toggleMenu} style={{ cursor: "pointer" }} />
        </div>

        <ul className="sidebar-links">
          <li>
            <Link href="/" onClick={toggleMenu}>
              Inicio
            </Link>
          </li>
          <li>
            <Link href={`/profile/${username}`} onClick={toggleMenu}>
              Cuenta
            </Link>
          </li>
          <li>
            <Link href="/opciones" onClick={toggleMenu}>
              Opciones
            </Link>
          </li>
          <li>
            <Link href="/soporte" onClick={toggleMenu}>
              Soporte
            </Link>
          </li>
          <hr className="sidebar-divider" />
          <li>
            <Link href="/cookies" onClick={toggleMenu}>
              Política de Cookies
            </Link>
          </li>
          <li>
            <Link href="/privacidad" onClick={toggleMenu}>
              Política de Privacidad
            </Link>
          </li>
        </ul>
      </nav>
    </>
  );
}