"use client";

import { useState } from "react";
import Link from "next/link";
import { PhotoData } from "./PhotoData";

import LikeButton from "@/app/atoms/LikeButton/LikeButton";
import DeleteIcon from "@mui/icons-material/Delete";
import CheckIcon from "@mui/icons-material/Check";
import CloseIcon from "@mui/icons-material/Close";
import { useRouter } from "next/navigation";

// Servicio eliminar post
import { deletePost } from "@/app/services/postService";

export default function PhotoCard(photoProps: PhotoData) {
    const photo = photoProps;
    const imageUrl = typeof photo.src === 'string' ? photo.src : photo.src.src;

    const router = useRouter();
    const [confirmando, setConfirmando] = useState(false);
    const [borrado, setBorrado] = useState(false);


    const handleDeleteClick = (e: React.MouseEvent) => {
        e.stopPropagation();
        e.preventDefault();
        setConfirmando(true);
    };


    const handleCancel = (e: React.MouseEvent) => {
        e.stopPropagation();
        e.preventDefault();
        setConfirmando(false);
    };


    const handleConfirm = async (e: React.MouseEvent) => {
        e.stopPropagation();
        e.preventDefault();

        try {
            // Eliminamos la foto a traves del servicio
            await deletePost(photo.id);
            setConfirmando(false);
            
            // Borramos la imagen de la lista en el frontend
            setBorrado(true)

            router.refresh();
        } catch (error) {
            console.error("Error al eliminar la foto:", error);
            setConfirmando(false);
        }
        
    };

    if (borrado) return null;

    return (
        <Link href={`/photo/${photo.id}`} className="photo-card-link">
            <div className="photo-card-container">
                <img
                    src={imageUrl}
                    alt={photo.title || "Photo"}
                    className="photo-card-image"
                />
                <div className="photo-card-overlay">
                    <div className="photo-card-info">
                        {photo.username && (
                            <span
                                className="photo-card-author"
                                onClick={(e) => {
                                    e.preventDefault();
                                    e.stopPropagation();
                                    router.push(`/profile/${photo.username}`);
                                }}
                            >
                                @{photo.username}
                            </span>
                        )}
                        {photo.title && <h2 className="photo-card-title">{photo.title}</h2>}
                        <div className="photo-card-bottom">
                            <LikeButton
                                postId={Number(photo.id)}
                                initialCount={photo.likes}
                                isLiked={photo.likedByUser}
                            />
                            <div className="photo-card-meta">
                                {photo.iso && <span>ISO {photo.iso}</span>}
                                {photo.iso && photo.aperture && <span> • </span>}
                                {photo.aperture && <span>f/{photo.aperture}</span>}
                            </div>
                        </div>
                    </div>
                </div>


                {photo.showDelete && !confirmando && (
                    <button className="delete-btn" onClick={handleDeleteClick}>
                        <DeleteIcon fontSize="small" />
                    </button>
                )}


                {confirmando && (
                    <div className="confirm-overlay">
                        <p>¿Seguro?</p>
                        <div className="confirm-buttons">
                            <button className="confirm-yes" onClick={handleConfirm}>
                                <CheckIcon fontSize="small" />
                            </button>
                            <button className="confirm-no" onClick={handleCancel}>
                                <CloseIcon fontSize="small" />
                            </button>
                        </div>
                    </div>
                )}
            </div>
        </Link>
    );
}