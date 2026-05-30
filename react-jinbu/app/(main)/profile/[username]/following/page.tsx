'use client';

import { use, useEffect, useState } from 'react';
import Link from 'next/link';
import './following.css';
import { getFollowingByUsername, getProfilePictureUrl } from '@/app/services/userService';

interface FollowingPageProps {
    params: Promise<{ username: string }>;
}

export default function FollowingPage({ params }: FollowingPageProps) {
    const { username } = use(params);
    const [following, setFollowing] = useState<any[]>([]);
    const [loading, setLoading] = useState(true);
    
    useEffect(() => {
        let isMounted = true;

        const loadFollowingData = async () => {
            try {
                const data = await getFollowingByUsername(username); 
                
                if (!data || data.length === 0) {
                    if (isMounted) {
                        setFollowing([]);
                        setLoading(false);
                    }
                    return;
                }

                const followingWithAvatars = await Promise.all(
                    data.map(async (followedUser: any) => {
                        try {
                            const s3AvatarUrl = await getProfilePictureUrl(followedUser.username);
                            
                            if (s3AvatarUrl && typeof s3AvatarUrl === 'string' && s3AvatarUrl.startsWith('http')) {
                                return { ...followedUser, avatarUrl: s3AvatarUrl };
                            }
                            
                            return { ...followedUser, avatarUrl: '/images/user.jpg' };
                        } catch (e) {
                            return { ...followedUser, avatarUrl: '/images/user.jpg' };
                        }
                    })
                );
                
                if (isMounted) {
                    setFollowing(followingWithAvatars);
                }
                
            } catch (error) {
                console.error("Error cargando la lista de seguidos:", error);
            } finally {
                if (isMounted) setLoading(false);
            }
        };

        if (username) {
            loadFollowingData();
        }

        return () => {
            isMounted = false;
        };
    }, [username]);

    if (loading) return <div style={{ padding: "2rem", textAlign: "center" }}>Cargando seguidos...</div>;

    return (
        <main className="follow-page">
            <div className="follow-page__header">
                <Link href={`/profile/${username}`} className="follow-page__back">← {username}</Link>
                <h2 className="follow-page__title">Siguiendo</h2>
            </div>

            <div className="follow-page__list">
                {following.length === 0 ? (
                    <div style={{ textAlign: 'center', padding: '1rem' }}>No sigue a ningún usuario aún.</div>
                ) : (
                    following.map((user) => (
                        <Link key={user.username} href={`/profile/${user.username}`} className="follow-page__user">
                            <img
                                src={user.avatarUrl}
                                alt={user.username}
                                className="follow-page__avatar"
                                crossOrigin="anonymous"
                            />
                            <span className="follow-page__username">@{user.username}</span>
                        </Link>
                    ))
                )}
            </div>
        </main>
    );
}