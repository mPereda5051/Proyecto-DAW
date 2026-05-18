"use client";

import { useState } from "react";
import FavoriteIcon from "@mui/icons-material/Favorite";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import "./likeButton.css";

interface LikeButtonProps {
    initialCount?: number;
}

export default function LikeButton({ initialCount = 0 }: LikeButtonProps) {
    const [liked, setLiked] = useState(false);
    const [count, setCount] = useState(initialCount);

    function handleClick(e: React.MouseEvent) {
        e.stopPropagation();
        e.preventDefault();
        if (liked) {
            setLiked(false);
            setCount(c => c - 1);
        } else {
            setLiked(true);
            setCount(c => c + 1);
        }
    }

    return (
        <button
            className={`like-button ${liked ? "liked" : ""}`}
            onClick={handleClick}
            aria-label={liked ? "Quitar like" : "Dar like"}
        >
            {liked ? <FavoriteIcon fontSize="small" /> : <FavoriteBorderIcon fontSize="small" />}
            <span className="like-count">{count}</span>
        </button>
    );
}
