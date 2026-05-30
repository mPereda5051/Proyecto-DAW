import { Comment } from "./models/comment";

const BASE_URL = process.env.NEXT_PUBLIC_API_URL || 'http://localhost:9090';
const API_BASE_URL = `${BASE_URL}/comment`;

export async function getCommentsByPostId(postId: number, token?: string | null): Promise<Comment[]> {
    const headers: HeadersInit = {};
    if (token) {
        headers["Authorization"] = token.startsWith("Bearer ") ? token : `Bearer ${token}`;
    }

    const response = await fetch(`${API_BASE_URL}/post/${postId}`, {
        method: "GET",
        headers: headers,
        cache: "no-store",
    });

    if (!response.ok) {
        if (response.status === 404) return [];
        throw new Error("Failed to fetch comments");
    }

    return response.json();
}

export async function addComment(postId: number, content: string, token: string): Promise<Comment> {
    const response = await fetch(`${API_BASE_URL}/post/${postId}`, {
        method: "POST",
        headers: {
            "Content-Type": "text/plain", // Better match for @RequestBody String in Spring
            "Authorization": token.startsWith("Bearer ") ? token : `Bearer ${token}`,
        },
        body: content,
    });

    if (!response.ok) {
        const errorData = await response.text();
        throw new Error(errorData || "Failed to post comment");
    }

    return response.json();
}
