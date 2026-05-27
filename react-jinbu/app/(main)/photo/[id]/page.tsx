import { notFound } from "next/navigation";
import "./photoDetail.css";
import SendIcon from '@mui/icons-material/Send';
import LikeButton from "@/app/atoms/LikeButton/LikeButton";
import { getPost } from "@/app/services/postService";

export default async function PhotoDetailPage(props: { params: Promise<{ id: string }> }) {
    const params = await props.params;
    const { id } = params;

    let post;
    try {
        post = await getPost(id);
    } catch (error) {
        notFound();
    }

    const imageUrl = `https://jinbu-s3-bucket.s3.us-east-1.amazonaws.com/${post.photo.id}${post.photo.extension}`;

    return (
        <div className="photo-detail-container">
            <div className="image-panel">
                <img 
                    src={imageUrl}
                    alt={post.title} 
                />
            </div>

            <div className="info-panel">
                <header className="info-header">
                    <h1>{post.title}</h1>
                    <div className="metadata">
                        <span>ISO {post.photo.iso}</span>
                        <span>F/{post.photo.aperture}</span>
                        <span>{post.photo.exposure}s</span>
                    </div>
                </header>

                <main className="comments-section">
                    <p className="post-content">{post.content}</p>
                    <div className="comment">
                        <span className="comment-user">usuario_123</span>
                        <span className="comment-text">dios mio que es eso?!!</span>
                    </div>
                </main>

                <div className="interaction-section">
                    <LikeButton initialCount={post.likes} postId={Number(id)} />
                </div>

                <footer className="comment-input-section">
                    <input 
                        type="text" 
                        placeholder="Añade un comentario..." 
                        className="comment-input"
                    />
                    <button className="post-button">
                        <SendIcon />
                    </button>
                </footer>
            </div>
        </div>
    );
}
