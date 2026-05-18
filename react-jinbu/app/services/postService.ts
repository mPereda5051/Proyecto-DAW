// Servicio que extraer el metadata de una foto. No guarda informacion en la base de datos
export const extractMetadata = async (photo: File) => {
    const bearerToken = getToken();

    if (!bearerToken) {
        throw new Error("UNAUTHORIZED");
    };

    const formData = new FormData();
    formData.append('file', photo);

    const response = await fetch('http://localhost:9090/images/extractMetadata', {
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

    const response = await fetch('http://localhost:9090/posts/upload', { 
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

export const getToken = () => {
    return localStorage.getItem('token');
};
