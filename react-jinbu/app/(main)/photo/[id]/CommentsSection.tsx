'use client';

import { useState } from 'react';
import SendIcon from '@mui/icons-material/Send';
import { Comment } from '@/app/services/models/comment';
import { addComment } from '@/app/services/commentService';

interface CommentsSectionProps {
    initialComments: Comment[];
    postId: number;
    token: string | null;
    postContent: string;
}

export default function CommentsSection({ initialComments, postId, token, postContent }: CommentsSectionProps) {
    const [comments, setComments] = useState<Comment[]>(initialComments);
    const [newComment, setNewComment] = useState('');
    const [loading, setLoading] = useState(false);

    const handlePostComment = async () => {
        if (!newComment.trim() || !token) return;

        setLoading(true);
        try {
            const addedComment = await addComment(postId, newComment, token);
            setComments([addedComment, ...comments]);
            setNewComment('');
        } catch (error: any) {
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
                        <span className="comment-user">{comment.user.username}</span>
                        <span className="comment-text">: {comment.content}</span>
                    </div>
                ))}
            </main>

            {token ? (
                <footer className="comment-input-section">
                    <input 
                        type="text" 
                        placeholder="Añade un comentario..." 
                        className="comment-input"
                        value={newComment}
                        onChange={(e) => setNewComment(e.target.value)}
                        disabled={loading}
                        onKeyDown={(e) => e.key === 'Enter' && handlePostComment()}
                    />
                    <button 
                        className="post-button" 
                        onClick={handlePostComment}
                        disabled={loading || !newComment.trim()}
                    >
                        <SendIcon />
                    </button>
                </footer>
            ) : (
                <footer className="comment-input-section">
                    <p style={{ color: '#888', fontSize: '0.9rem' }}>Inicia sesión para comentar.</p>
                </footer>
            )}
        </>
    );
}
