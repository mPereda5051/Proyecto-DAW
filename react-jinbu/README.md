# Jinbu 📷

Red social para fotógrafos. Permite compartir fotografías con sus metadatos técnicos (ISO, apertura, exposición), explorar el trabajo de otros usuarios y gestionar tu perfil.

## Tecnologías usadas

- Next.js
- TypeScript
- React
- Material UI
- Notistack
- CSS

## Instalación y uso

1. Clona el repositorio:

```bash
git clone https://github.com/mPereda5051/Proyecto-DAW.git
```

2. Entra en la carpeta del frontend e instala las dependencias:

```bash
cd react-jinbu
npm install
```

3. Arranca el servidor de desarrollo:

```bash
npm run dev
```

4. Abre [http://localhost:3000](http://localhost:3000) en el navegador.

> ⚠️ El backend (Spring Boot) debe estar corriendo en `http://localhost:9090` para que la app funcione correctamente.

## Estructura del proyecto

```
app/
├── atoms/           # Componentes básicos reutilizables (Button, Input, LikeButton...)
├── molecule/        # Componentes formados por átomos (FormField, PhotoCard, ProfileHeader...)
├── organisms/       # Componentes complejos que forman secciones enteras (Navbar, ProfileSection...)
├── services/        # Llamadas a la API del backend (auth, posts, usuarios)
├── (auth)/          # Páginas públicas: login y registro
│   ├── login/
│   └── register/
└── (main)/          # Páginas privadas (requieren sesión iniciada)
    ├── page.tsx         # Página principal — galería de fotos
    ├── photo/[id]/      # Detalle de una fotografía
    ├── profile/[username]/ # Perfil de usuario
    ├── uploadimage/     # Subir una fotografía
    ├── opciones/        # Configuración de la cuenta
    └── soporte/         # Soporte, cookies y privacidad
```
