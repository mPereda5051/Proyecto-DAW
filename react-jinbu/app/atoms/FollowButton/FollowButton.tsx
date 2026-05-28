"use client";

import { useState } from "react";
import "./FollowButton.css"
import { useSnackbar } from "notistack";

interface FollowButtonProps {
    username: string;
    isFollowing: boolean;
}

export default function FollowButton ({username, isFollowing}: FollowButtonProps){
    const {enqueueSnackbar } = useSnackbar();
    const [following, setFollowing] = useState(isFollowing);
    const [loading, setLoading] = useState(false);

    async function handleClick() {
        if (loading) return;
        setLoading(true);

        try {
            if (following) {
                setFollowing(false);
            } else {
                setFollowing(true);
            }
        } catch (error) {
            console.error("Error al procesar el Follow", error);
            enqueueSnackbar ("Error al procesar la acción", {variant: "error"})
        }finally {
            setLoading(false)
        }
    }

    return (
        <button
        className={`follow-button ${following ? "following" : ""}`}
        onClick={handleClick}
        disabled={loading}
        >
            {following ? "Siguiendo" : "Seguir"}

        </button>
    )

    
}