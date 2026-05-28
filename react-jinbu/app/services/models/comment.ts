export interface Comment {
    id: number;
    content: string;
    createdAt: string;
    user: {
        username: string;
    };
}
