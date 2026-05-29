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

    return response.json();
};

//funcion para obtener los datos del usuario
export const getCurrentUser = async () => {
    const token = getToken();
    
    const response = await fetch(`${BASE_URL}/users/me`, {
        method: 'GET',
        headers: {
            'Authorization': token ? (token.startsWith('Bearer ') ? token : `Bearer ${token}`) : '',
            'Content-Type': 'application/json',
        },
    });

    if (!response.ok) {
        throw new Error('No se pudo cargar el usuario actual');
    }

    return response.json();
};

//funcion para actualizar los datos del usuario
export const updateProfile = async (userData: { username: string; email: string; name: string }) => {
    const token = getToken();

    const response = await fetch(`${BASE_URL}/users/me`, {
        method: 'PUT',
        headers: {
            'Authorization': token ? (token.startsWith('Bearer ') ? token : `Bearer ${token}`) : '',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(userData),
    });

    if (!response.ok) {
        throw new Error('No se pudo actualizar el perfil');
    }

    return response;
};

//funcion para cambiar contraseña
export const changePassword = async (passwordData: { currentPassword: string; newPassword: string }) => {
    const token = getToken();

    const response = await fetch(`${BASE_URL}/users/me/password`, {
        method: 'PUT',
        headers: {
            'Authorization': token ? (token.startsWith('Bearer ') ? token : `Bearer ${token}`) : '',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(passwordData),
    });

    if (!response.ok) {
        throw new Error('No se pudo cambiar la contraseña');
    }

    return response;
};

//funcion para seguir o dejar de seguir a un usuario
export const toggleFollow = async (username: string) => {
    const token = getToken();

    const response = await fetch(`${BASE_URL}/users/follow/${username}`, {
        method: 'POST',
        headers: {
            'Authorization': token ? (token.startsWith('Bearer ') ? token : `Bearer ${token}`) : '',
            'Content-Type': 'application/json',
        },
    });

    if (!response.ok) {
        throw new Error('No se pudo procesar el follow');
    }

    return response;
};

