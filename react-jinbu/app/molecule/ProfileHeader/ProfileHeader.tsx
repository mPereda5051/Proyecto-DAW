import './ProfileHeader.css';
import AvatarImage from '@/app/atoms/AvatarImage/AvatarImage';

interface ProfileHeaderProps {
    avatarSrc: string;
    avatarAlt: string;
    username: string;
    bio?: string;
}

export default function ProfileHeader({ avatarSrc, avatarAlt, username, bio }: ProfileHeaderProps) {
    return (
        <div className="profile-header">
            <AvatarImage src={avatarSrc} alt={avatarAlt} />
            <div className="profile-header-info">
                <h2 className="profile-header-username">{username}</h2>
                {bio && <p className='profile-header-bio'>{bio}</p>}
            </div>
        </div>
    );
}