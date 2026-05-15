import { getToken } from "./authService";

const BASE_URL = 'http://localhost:9090';

// Función para obtener los datos básicos del perfil de un usuario
export const getUserProfile = async (username: string) => {
    const token = getToken();
    
    const response = await fetch(`${BASE_URL}/admin/user/profile/${username}`, {
        method: 'GET',
        headers: {
            'Authorization': token ? (token.startsWith('Bearer ') ? token : `Bearer ${token}`) : '',
            'Content-Type': 'application/json',
        },
    });

    if (!response.ok) {
        throw new Error('No se pudo cargar el perfil');
    }

    return response.json();
};

// Función para obtener todas las fotos/posts de un usuario
export const getUserPosts = async (username: string) => {
    const token = getToken();

    const response = await fetch(`${BASE_URL}/posts/user/${username}`, {
        method: 'GET',
        headers: {
            'Authorization': token ? (token.startsWith('Bearer ') ? token : `Bearer ${token}`) : '',
            'Content-Type': 'application/json',
        },
    });

    if (!response.ok) {
        throw new Error('No se pudieron cargar las fotos');
    }

    return response.json();
};
