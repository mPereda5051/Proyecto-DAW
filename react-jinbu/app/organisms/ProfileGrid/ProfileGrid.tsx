import './ProfileGrid.css';
import PhotoCard from '@/app/molecule/PhotoCardComponent/PhotoCard';
import { PhotoData } from '@/app/molecule/PhotoCardComponent/PhotoData';

interface ProfileGridProps {
    photos: PhotoData[];
}

export default function ProfileGrid({ photos }: ProfileGridProps) {
    return (
        <div className="profile-grid">
            {photos.map((photo) => (
                <PhotoCard key={photo.id} {...photo} />
            ))}
        </div>
    );
}
