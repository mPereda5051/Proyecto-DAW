import './ProfileGrid.css';
import PhotoCard from '@/app/molecule/PhotoCardComponent/PhotoCard';
import { PhotoData } from '@/app/molecule/PhotoCardComponent/PhotoData';

interface ProfileGridProps {
    photos: PhotoData[];
    showDelete?: boolean;
}

export default function ProfileGrid({ photos, showDelete }: ProfileGridProps) {
    return (
        <div className="profile-grid">
            {photos.map((photo) => (
                <PhotoCard key={photo.id} {...photo} showDelete={showDelete} />
            ))}
        </div>
    );
}
