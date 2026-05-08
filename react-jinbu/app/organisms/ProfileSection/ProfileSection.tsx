import './ProfileSection.css';
import ProfileHeader from '@/app/molecule/ProfileHeader/ProfileHeader';
import ProfileStats from '@/app/molecule/ProfileStats/ProfileStats';

interface ProfileSectionProps {
    avatarSrc: string;
    avatarAlt: string;
    username: string;
    bio?: string;
    posts: number;
    followers: number;
    following: number;
}

export default function ProfileSection({ avatarSrc, avatarAlt, username, bio, posts, followers, following }: ProfileSectionProps) {
    return (
        <div className="profile-section">
            <ProfileHeader avatarSrc={avatarSrc} avatarAlt={avatarAlt} username={username} bio={bio} />
            <ProfileStats posts={posts} followers={followers} following={following} />
        </div>
    );
}
