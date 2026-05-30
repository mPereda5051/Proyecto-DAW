import './AvatarImage.css';

interface AvatarImageProps {
    src: string;
    alt: string;
}

/** Imagen de avatar circular reutilizable en el perfil y la navbar. */
export default function AvatarImage({ src, alt }: AvatarImageProps) {
    return (
        <img className="avatar-image" src={src} alt={alt} crossOrigin="anonymous" />
    );
}