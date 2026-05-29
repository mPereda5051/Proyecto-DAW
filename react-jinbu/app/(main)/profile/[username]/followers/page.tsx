'use client';

import { use, useEffect, useState } from 'react';
import Link from 'next/link';
import './followers.css';
import { getFollowersByUsername } from '@/app/services/userService';

const FAKE_FOLLOWERS = [
    { username: 'maria_photo', avatarUrl: 'https://i.pravatar.cc/150?img=1' },
    { username: 'carlos_lens', avatarUrl: 'https://i.pravatar.cc/150?img=2' },
    { username: 'laura_shots', avatarUrl: 'https://i.pravatar.cc/150?img=3' },
    { username: 'pepe_foto', avatarUrl: 'https://i.pravatar.cc/150?img=4' },
];

interface FollowersPageProps {
    params: Promise<{ username: string }>;
} 

export default function FollowersPage({ params }: FollowersPageProps) {
    const { username } = use(params);
    const [followers, setFollowers] = useState<any[]>([]);

    useEffect(() => {
        const loadFollowersData = async () => {
            try {
                const data = await getFollowersByUsername(username); 
                
                setFollowers(data || []);
                
            } catch (error) {
                console.error("Error cargando la lista de seguidos:", error);
            } 
        };

        if (username) {
            loadFollowersData();
        }
    }, [username]);

    return (
        <main className="follow-page">
            <div className="follow-page__header">
                <Link href={`/profile/${username}`} className="follow-page__back">← {username}</Link>
                <h2 className="follow-page__title">Seguidores</h2>
            </div>

            <div className="follow-page__list">
                {followers?.map((user) => (
                    <Link key={user.username} href={`/profile/${user.username}`} className="follow-page__user">
                        <img
                            src={user.avatarUrl || 'https://i.pravatar.cc/150'}
                            alt={user.username}
                            className="follow-page__avatar"
                        />
                        <span className="follow-page__username">@{user.username}</span>
                    </Link>
                ))}
            </div>
        </main>
    );
}
