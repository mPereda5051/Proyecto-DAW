'use client';

import { useState, useEffect } from 'react';
import SendIcon from '@mui/icons-material/Send';
import { Comment } from '@/app/services/models/comment';
import { addComment } from '@/app/services/commentService';
import Link from 'next/link';
import { useSnackbar } from 'notistack';

interface CommentsSectionProps {
    initialComments: Comment[];
    postId: number;
    token: string | null;
    postContent: string;
}

export default function CommentsSection({ initialComments, postId, token, postContent }: CommentsSectionProps) {
    const { enqueueSnackbar } = useSnackbar();
    const [comments, setComments] = useState<Comment[]>(initialComments);
    const [newComment, setNewComment] = useState('');
    const [loading, setLoading] = useState(false);
    const [cooldown, setCooldown] = useState(0);

    useEffect(() => {
        if (cooldown > 0) {
            const timer = setTimeout(() => setCooldown(cooldown - 1), 1000);
            return () => clearTimeout(timer);
        }
    }, [cooldown]);

    const handlePostComment = async () => {
        if (!newComment.trim() || !token || cooldown > 0) return;

        setLoading(true);
        try {
            const addedComment = await addComment(postId, newComment, token);
            setComments([addedComment, ...comments]);
            setNewComment('');
            setCooldown(10);
            enqueueSnackbar('Comentario publicado', { variant: 'success' });
        } catch (error: any) {
            console.error("Error al publicar comentario:", error);
            enqueueSnackbar(error.message || 'Error al publicar el comentario', { variant: 'error' });
        } finally {
            setLoading(false);
        }
    };

    return (
        <>
            <main className="comments-section">
                <p className="post-content">{postContent}</p>
                {comments.length === 0 && <p style={{ color: '#888', fontSize: '0.9rem' }}>No hay comentarios aún. ¡Sé el primero!</p>}
                {comments.map((comment) => (
                    <div key={comment.id} className="comment">
                        <Link href={`/profile/${comment.user.username}`} className="comment-user">{comment.user.username}</Link>
                        <span className="comment-text">: {comment.content}</span>
                    </div>
                ))}
            </main>

            {token ? (
                <footer className="comment-input-section-container">
                    <div className="comment-input-section">
                        <input 
                            type="text" 
                            placeholder={cooldown > 0 ? `Espera ${cooldown}s para comentar...` : "Añade un comentario..."}
                            className="comment-input"
                            value={newComment}
                            onChange={(e) => setNewComment(e.target.value)}
                            disabled={loading || cooldown > 0}
                            onKeyDown={(e) => e.key === 'Enter' && handlePostComment()}
                        />
                        <button 
                            className="post-button" 
                            onClick={handlePostComment}
                            disabled={loading || !newComment.trim() || cooldown > 0}
                            style={{ position: 'relative' }}
                        >
                            {cooldown > 0 ? (
                                <span style={{ fontSize: '0.8rem', fontWeight: 'bold' }}>{cooldown}s</span>
                            ) : (
                                <SendIcon />
                            )}
                        </button>
                    </div>
                    {cooldown > 0 && (
                        <p className="cooldown-text" style={{ 
                            fontSize: '0.75rem', 
                            color: '#666', 
                            marginTop: '4px',
                            marginLeft: '12px',
                            fontStyle: 'italic' 
                        }}>
                            Podrás escribir otro comentario en {cooldown} segundos.
                        </p>
                    )}
                </footer>
            ) : (
                <footer className="comment-input-section">
                    <p style={{ color: '#888', fontSize: '0.9rem' }}>Inicia sesión para comentar.</p>
                </footer>
            )}
        </>
    );
}
