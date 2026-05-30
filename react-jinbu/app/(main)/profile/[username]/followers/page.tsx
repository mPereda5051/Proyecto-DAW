'use client';

import { use, useEffect, useState } from 'react';
import Link from 'next/link';
import './followers.css';
import { getFollowersByUsername, getProfilePictureUrl } from '@/app/services/userService';

interface FollowersPageProps {
    params: Promise<{ username: string }>;
} 

export default function FollowersPage({ params }: FollowersPageProps) {
    const { username } = use(params);
    const [followers, setFollowers] = useState<any[]>([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        let isMounted = true;

        const loadFollowersData = async () => {
            try {
                const data = await getFollowersByUsername(username); 
                
                if (!data || data.length === 0) {
                    if (isMounted) {
                        setFollowers([]);
                        setLoading(false);
                    }
                    return;
                }

                const followersWithAvatars = await Promise.all(
                    data.map(async (follower: any) => {
                        try {
                            const s3AvatarUrl = await getProfilePictureUrl(follower.username);
                            
                            if (s3AvatarUrl && typeof s3AvatarUrl === 'string' && s3AvatarUrl.startsWith('http')) {
                                return { ...follower, avatarUrl: s3AvatarUrl };
                            }
                            
                            return { ...follower, avatarUrl: '/images/user.jpg' };
                        } catch (e) {
                            return { ...follower, avatarUrl: '/images/user.jpg' };
                        }
                    })
                );

                if (isMounted) {
                    setFollowers(followersWithAvatars);
                }
                
            } catch (error) {
                console.error("Error cargando la lista de seguidores:", error);
            } finally {
                if (isMounted) setLoading(false);
            }
        };

        if (username) {
            loadFollowersData();
        }

        return () => {
            isMounted = false;
        };
    }, [username]);

    if (loading) return <div style={{ padding: "2rem", textAlign: "center" }}>Cargando seguidores...</div>;

    return (
        <main className="follow-page">
            <div className="follow-page__header">
                <Link href={`/profile/${username}`} className="follow-page__back">← {username}</Link>
                <h2 className="follow-page__title">Seguidores</h2>
            </div>

            <div className="follow-page__list">
                {followers.length === 0 ? (
                    <div style={{ textAlign: 'center', padding: '1rem' }}>No hay seguidores aún.</div>
                ) : (
                    followers.map((user) => (
                        <Link key={user.username} href={`/profile/${user.username}`} className="follow-page__user">
                            <img
                                src={user.avatarUrl}
                                alt={user.username}
                                className="follow-page__avatar"
                            />
                            <span className="follow-page__username">@{user.username}</span>
                        </Link>
                    ))
                )}
            </div>
        </main>
    );
}