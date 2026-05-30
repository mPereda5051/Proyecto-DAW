import { notFound } from "next/navigation";
import "./photoDetail.css";
import { cookies } from "next/headers";
import Link from "next/link";
import LikeButton from "@/app/atoms/LikeButton/LikeButton";
import { getPost } from "@/app/services/postService";
import { getCommentsByPostId } from "@/app/services/commentService";
import CommentsSection from "./CommentsSection";


export default async function PhotoDetailPage(props: { params: Promise<{ id: string }> }) {
    const params = await props.params;
    const { id } = params;

    const cookieStore = await cookies();
    const token = cookieStore.get("token")?.value || null;
    let post;
    let initialComments: any[] = [];
    
    try {
        post = await getPost(id, token);
        initialComments = await getCommentsByPostId(Number(id), token);
    } catch (error) {
        if (!post) notFound();
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
                    {post.username && (
                        <Link href={`/profile/${post.username}`} className="post-author">
                            @{post.username}
                        </Link>
                    )}
                    <div className="metadata">
                        <span>ISO {post.photo.iso ?? 100}</span>
                        <span>F/{post.photo.aperture ?? 2.8}</span>
                        <span>{post.photo.exposure ?? '1/125'}s</span>
                    </div>
                </header>

                <CommentsSection 
                    initialComments={initialComments}
                    postId={Number(id)}
                    token={token}
                    postContent={post.content}
                />

                <div className="interaction-section">
                    <LikeButton 
                        initialCount={post.likes} 
                        postId={Number(id)} 
                        isLiked={post.likedByUser}
                    />
                </div>
            </div>
        </div>
    );
}
