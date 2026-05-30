const BASE_URL = process.env.NEXT_PUBLIC_API_URL || 'http://localhost:9090';

// Servicio que extraer el metadata de una foto. No guarda informacion en la base de datos
export const extractMetadata = async (photo: File) => {
    const bearerToken = getToken();

    if (!bearerToken) {
        throw new Error("UNAUTHORIZED");
    };

    const formData = new FormData();
    formData.append('file', photo);

    const response = await fetch(`${BASE_URL}/images/extractMetadata`, {
        method: 'POST',
        headers: {
            'Authorization': bearerToken.startsWith('Bearer ') ? bearerToken : `Bearer ${bearerToken}`, // Usamos el token de atuentificacion
        },
        body: formData,
    });

    if (!response.ok) {
        throw new Error("Error en la subida");
    };

    return response.json();
};

import { Post } from "./models/post";
import { Photo } from "./models/photo";

// Servicio para subir foto y post a la vez
export const uploadPhotoAndPost = async (post: Post, photo: Photo, file: File) => {
    const bearerToken = getToken();

    if (!bearerToken) {
        throw new Error("UNAUTHORIZED");
    }

    const formData = new FormData();
    formData.append(
        'post',
        new Blob([JSON.stringify(post)], { type: 'application/json' })
    );

    formData.append(
        'photo',
        new Blob([JSON.stringify(photo)], { type: 'application/json' })
    );

    formData.append('file', file);

    const response = await fetch(`${BASE_URL}/posts/upload`, {
        method: 'POST',
        headers: {
            'Authorization': bearerToken.startsWith('Bearer ') ? bearerToken : `Bearer ${bearerToken}`,
        },
        body: formData,
    });

    if (!response.ok) {
        throw new Error("Error al guardar el post y la foto");
    }
    return response;
};

// Servicio para devolver post con ids desde la base de datos con paginacion
export const retrievePostsWithPagination = async (pageNumber: number) => {
    const bearerToken = getToken();

    const url = `${BASE_URL}/posts/retrieve/${pageNumber}?page=${pageNumber}`;

    const headers: HeadersInit = {};
    if (bearerToken) {
        headers['Authorization'] = bearerToken.startsWith('Bearer ') ? bearerToken : `Bearer ${bearerToken}`;
    }

    const response = await fetch(url, {
        method: 'GET',
        headers: headers
    });

    if (!response.ok) {
        throw new Error("Error al obtener la página de posts");
    }

    return response.json();
};

// Servicio para devolver post de un usuario con ids desde la base de datos con paginacion
export const retrieveUserPostsWithPagination = async (userId: number, pageNumber: number) => {
    const bearerToken = getToken();

    const url = `${BASE_URL}/posts/${userId}/all?page=${pageNumber}`;

    const headers: HeadersInit = {};
    if (bearerToken) {
        headers['Authorization'] = bearerToken.startsWith('Bearer ') ? bearerToken : `Bearer ${bearerToken}`;
    }

    const response = await fetch(url, {
        method: 'GET',
        headers: headers
    });

    if (!response.ok) {
        throw new Error(`Error al obtener la página de posts del usuario ${userId}`);
    }

    return response.json();
};

// Servicio para eliminar post mediante su id
export const deletePost = async (id: number) => {
    const bearerToken = getToken();

    if (!bearerToken) {
        throw new Error("UNAUTHORIZED");
    }

    const url = `${BASE_URL}/posts/${id}`;

    const response = await fetch(url, {
        method: 'DELETE',
        headers: {
            'Authorization': bearerToken.startsWith('Bearer ') ? bearerToken : `Bearer ${bearerToken}`,
        },
    });

    if (!response.ok) {
        throw new Error(`Error al obtener la página de posts del usuario ${id}`);
    }

    return response;
};

export const getPost = async (id: string, serverToken?: string | null) => {
    const bearerToken = serverToken !== undefined ? serverToken : getToken();

    const url = `${BASE_URL}/posts/${id}`;

    const headers: HeadersInit = {};
    if (bearerToken) {
        headers['Authorization'] = bearerToken.startsWith('Bearer ') ? bearerToken : `Bearer ${bearerToken}`;
    }

    const response = await fetch(url, {
        method: 'GET',
        headers: headers
    });

    if (!response.ok) {
        throw new Error("Error al obtener el post");
    }

    return response.json();
};

export const likePost = async (id: number) => {
    const bearerToken = getToken();
    if (!bearerToken) throw new Error("UNAUTHORIZED");

    const response = await fetch(`${BASE_URL}/posts/${id}/like`, {
        method: 'PUT',
        headers: {
            'Authorization': bearerToken.startsWith('Bearer ') ? bearerToken : `Bearer ${bearerToken}`,
        },
    });

    if (!response.ok) {
        throw new Error("Error al dar like");
    }

    return response;
};

export const dislikePost = async (id: number) => {
    const bearerToken = getToken();
    if (!bearerToken) throw new Error("UNAUTHORIZED");

    const response = await fetch(`${BASE_URL}/posts/${id}/dislike`, {
        method: 'PUT',
        headers: {
            'Authorization': bearerToken.startsWith('Bearer ') ? bearerToken : `Bearer ${bearerToken}`,
        },
    });

    if (!response.ok) {
        throw new Error("Error al quitar like");
    }

    return response;
};

export const getToken = () => {
    if (typeof window !== 'undefined') {
        return localStorage.getItem('token');
    }
    return null;
};