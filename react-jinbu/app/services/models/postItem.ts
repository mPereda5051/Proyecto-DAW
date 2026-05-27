export interface PostItem {
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