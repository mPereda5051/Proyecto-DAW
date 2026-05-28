'use client';
import { useState, useEffect } from 'react';

import './ProfileGrid.css';
import PhotoCard from '@/app/molecule/PhotoCardComponent/PhotoCard';
import { PhotoData } from '@/app/molecule/PhotoCardComponent/PhotoData';
import { retrievePostsWithPagination } from '@/app/services/postService';
import { retrieveUserPostsWithPagination } from '@/app/services/postService';

interface ProfileGridProps {
    userId: any;
    showDelete?: boolean;
}

interface PostItem {
    id: number;
    title: string;
    content: string;
    userId: number;
    username: string;
    likes: number;
    likedByUser: boolean;
    photo: {
        fullUrl: string;
        iso?: number;
        aperture?: number;
    } | null;
}

export default function ProfileGrid({ userId, showDelete }: ProfileGridProps) {

    const [photos, setPost] = useState<PostItem[]>([]);

    const [pageNumber, setPageNumber] = useState<number>(0);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchPosts = async () => {
            setLoading(true);
            setError(null);

            try {
                const data = await retrieveUserPostsWithPagination(userId, pageNumber);
                const fetchedPosts = data.content ? data.content : data;
                setPost(fetchedPosts);
            } catch (err: any) {
                setError(err.message || "Ocurrió un error al cargar las imágenes");
            } finally {
                setLoading(false);
            }
        };

        fetchPosts();
    }, [pageNumber]);

    if (loading) return <p>Cargando galería...</p>;
    if (error) return <p>Error: {error}</p>;

    return (
        <><div className="profile-grid">
            {photos.map((item) => (
                <PhotoCard
                    key={item.id}
                    id={item.id}
                    src={item.photo?.fullUrl ?? ''}
                    title={item.title || "Sin título"}
                    likes={item.likes}
                    likedByUser={item.likedByUser}
                    showDelete={showDelete} />
            ))}
        </div><div className="pagination-controls" style={{ display: 'flex', gap: '1rem', marginTop: '2rem' }}>
                <button
                    onClick={() => setPageNumber((prev) => Math.max(prev - 1, 0))}
                    disabled={pageNumber === 0}
                >
                    Anterior
                </button>
                <span>Página {pageNumber}</span>
                <button onClick={() => setPageNumber((prev) => prev + 1)}>
                    Siguiente
                </button>
            </div></>
    );
}
