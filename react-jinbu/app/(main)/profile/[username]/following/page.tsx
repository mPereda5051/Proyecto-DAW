'use client';

import { use } from 'react';
import Link from 'next/link';
import './following.css';

const FAKE_FOLLOWING = [
    { username: 'ana_clicks', avatarUrl: 'https://i.pravatar.cc/150?img=5' },
    { username: 'jorge_raw', avatarUrl: 'https://i.pravatar.cc/150?img=6' },
    { username: 'sofia_frame', avatarUrl: 'https://i.pravatar.cc/150?img=7' },
];

interface FollowingPageProps {
    params: Promise<{ username: string }>;
}

export default function FollowingPage({ params }: FollowingPageProps) {
    const { username } = use(params);

    return (
        <main className="follow-page">
            <div className="follow-page__header">
                <Link href={`/profile/${username}`} className="follow-page__back">← {username}</Link>
                <h2 className="follow-page__title">Siguiendo</h2>
            </div>

            <div className="follow-page__list">
                {FAKE_FOLLOWING.map((user) => (
                    <Link key={user.username} href={`/profile/${user.username}`} className="follow-page__user">
                        <img
                            src={user.avatarUrl}
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
