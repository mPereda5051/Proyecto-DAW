"use client";

import { useState } from "react";
import Link from "next/link";
import { PhotoData } from "./PhotoData";
import LikeButton from "@/app/atoms/LikeButton/LikeButton";
import DeleteIcon from "@mui/icons-material/Delete";
import CheckIcon from "@mui/icons-material/Check";
import CloseIcon from "@mui/icons-material/Close";

export default function PhotoCard(photoProps: PhotoData) {
    const photo = photoProps;
    const imageUrl = typeof photo.src === 'string' ? photo.src : photo.src.src;

    
    const [confirmando, setConfirmando] = useState(false);

    
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

    
    const handleConfirm = (e: React.MouseEvent) => {
        e.stopPropagation();
        e.preventDefault();
        setConfirmando(false);
    };

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
                        {photo.title && <h2 className="photo-card-title">{photo.title}</h2>}
                        <div className="photo-card-bottom">
                            <LikeButton postId={Number(photo.id)} />
                            <div className="photo-card-meta">
                                <span>ISO 100</span>
                                <span> • </span>
                                <span>f/2.8</span>
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