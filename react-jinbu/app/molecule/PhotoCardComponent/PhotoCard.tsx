
import Link from "next/link";
// PhotoData antiguo, cambiar futuro
import { PhotoData } from "./PhotoData";

import { PostItem } from "@/app/services/models/postItem";
import LikeButton from "@/app/atoms/LikeButton/LikeButton";

export default function PhotoCard(photoProps: PhotoData) {
    const photo = photoProps;
    const imageUrl = typeof photo.src === 'string' ? photo.src : photo.src.src;
    
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
            </div>
        </Link>
    );
}