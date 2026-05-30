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

//funcion para obtener los datos del usuario mediante nombre de usuario
export const getFollowersByUsername = async (username: String) => {
    const token = getToken();
    
    const response = await fetch(`${BASE_URL}/users/${username}/followers`, {
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

export const getFollowingByUsername = async (username: String) => {
    const token = getToken();
    
    const response = await fetch(`${BASE_URL}/users/${username}/following`, {
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

// Funcion para obtener la foto de perfil del usuario
export const getProfilePictureUrl = async (username: string) => {
    const token = getToken();
    
    const response = await fetch(`${BASE_URL}/images/profilePicture/${username}`, {
        method: 'GET',
        headers: {
            'Authorization': token ? (token.startsWith('Bearer ') ? token : `Bearer ${token}`) : '',
            'Content-Type': 'application/json',
        },
    });

    if (response.status === 404) {
        throw new Error('Imagen no encontrada');
    }

    if (!response.ok) {
        throw new Error('No se pudo obtener la URL de la foto de perfil');
    }

    return response.text();
};

// Funcion para subir la foto de perfil al s3
export const uploadProfilePicture = async (file: File) => {
    const bearerToken = getToken();

    if (!bearerToken) {
        throw new Error("UNAUTHORIZED");
    }

    const formData = new FormData();
    formData.append('file', file);

    const response = await fetch(`${BASE_URL}/images/profilePicture/upload`, {
        method: 'POST',
        headers: {
            'Authorization': bearerToken.startsWith('Bearer ') ? bearerToken : `Bearer ${bearerToken}`,
        },
        body: formData,
    });

    if (!response.ok) {
        throw new Error("Error al guardar la foto de perfil");
    }
    
    return response;
};

// Función para eliminar la foto de perfil del S3
export const deleteProfilePicture = async () => {
    const token = getToken();

    const response = await fetch(`${BASE_URL}/images/profilePicture`, {
        method: 'DELETE', 
        headers: {
            'Authorization': token ? (token.startsWith('Bearer ') ? token : `Bearer ${token}`) : '',
            'Content-Type': 'application/json',
        },
    });

    if (response.status === 404) {
        throw new Error('La foto de perfil no existe o ya fue eliminada');
    }

    if (!response.ok) {
        throw new Error('No se pudo eliminar la foto de perfil');
    }

    return true; 
};