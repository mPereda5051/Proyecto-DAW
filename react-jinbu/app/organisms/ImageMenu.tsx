'use client';

import { useState, useEffect } from 'react';
import PhotoCard from '../molecule/PhotoCardComponent/PhotoCard';
import img1 from './testimg/horizontal/kanenori-train-6907884.jpg';
import { retrievePostsWithPagination } from '../services/postService';

interface PostItem {
    id: number;
    title: string;
    content: string;
    userId: number;
    username: string;
    likes: number;
    photo: {
        fullUrl: string;
        iso?: number;
        aperture?: number;
    } | null;
}

export default function ImageMenu() {
    const [photos, setPhotos] = useState<PostItem[]>([]);
    const [pageNumber, setPageNumber] = useState<number>(0);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchPosts = async () => {
            setLoading(true);
            setError(null);

            try {
                const data = await retrievePostsWithPagination(pageNumber);
                const fetchedPosts = data.content ? data.content : data;
                setPhotos(fetchedPosts);
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
        <main className="main">
            <div className="gallery-container">
                {photos.map((item) => (
                    console.log(item.photo?.fullUrl),
                    <PhotoCard
                        key={item.id}
                        id={item.id}
                        src={item.photo?.fullUrl ?? img1}
                        title={item.title || "Sin título"}
                    />
                ))}
            </div>

            <div className="pagination-controls" style={{ display: 'flex', gap: '1rem', marginTop: '2rem' }}>
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
            </div>
        </main>
    );
}