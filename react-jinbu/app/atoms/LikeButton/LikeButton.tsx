"use client";

import { useState } from "react";
import FavoriteIcon from "@mui/icons-material/Favorite";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import "./likeButton.css";
import { likePost, dislikePost } from "@/app/services/postService";

interface LikeButtonProps {
    initialCount?: number;
    postId: number;
}

export default function LikeButton({ initialCount = 0, postId }: LikeButtonProps) {
    const [liked, setLiked] = useState(false);
    const [count, setCount] = useState(initialCount);
    const [loading, setLoading] = useState(false);

    async function handleClick(e: React.MouseEvent) {
        e.stopPropagation();
        e.preventDefault();
        
        if (loading) return;
        setLoading(true);

        try {
            if (liked) {
                await dislikePost(postId);
                setLiked(false);
                setCount(c => c - 1);
            } else {
                await likePost(postId);
                setLiked(true);
                setCount(c => c + 1);
            }
        } catch (error) {
            console.error("Error al procesar el like:", error);
            // Podríamos añadir una notificación al usuario aquí
        } finally {
            setLoading(false);
        }
    }

    return (
        <button
            className={`like-button ${liked ? "liked" : ""} ${loading ? "loading" : ""}`}
            onClick={handleClick}
            aria-label={liked ? "Quitar like" : "Dar like"}
            disabled={loading}
        >
            {liked ? <FavoriteIcon fontSize="small" /> : <FavoriteBorderIcon fontSize="small" />}
            <span className="like-count">{count}</span>
        </button>
    );
}
