import './ProfileSection.css';
import ProfileHeader from '@/app/molecule/ProfileHeader/ProfileHeader';
import ProfileStats from '@/app/molecule/ProfileStats/ProfileStats';
import FollowButton from '@/app/atoms/FollowButton/FollowButton';

interface ProfileSectionProps {
    avatarSrc: string;
    avatarAlt: string;
    username: string;
    bio?: string;
    posts: number;
    followers: number;
    following: number;
    showFollowButton?: boolean;
    isFollowing?: boolean;
}

/** Sección superior del perfil: agrupa el header, las estadísticas y el botón de seguir. */
export default function ProfileSection({ avatarSrc, avatarAlt, username, bio, posts, followers, following, showFollowButton = false, isFollowing = false }: ProfileSectionProps) {
    return (
        <div className="profile-section">
            <div className="profile-section-header-wrapper">
                <ProfileHeader avatarSrc={avatarSrc} avatarAlt={avatarAlt} username={username} bio={bio} />
                {showFollowButton && (
                    <div className="profile-section-action">
                        <FollowButton username={username} isFollowing={isFollowing} />
                    </div>
                )}
            </div>
            <ProfileStats posts={posts} followers={followers} following={following} username={username} />
        </div>
    );
}
