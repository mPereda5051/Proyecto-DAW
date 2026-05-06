import './AvatarImage.css';

interface AvatarImageProps {
    src: string;
    alt: string;
}

export default function AvatarImage({ src, alt }: AvatarImageProps) {
    return (
        <img className="avatar-image" src={src} alt={alt} />
    );
}