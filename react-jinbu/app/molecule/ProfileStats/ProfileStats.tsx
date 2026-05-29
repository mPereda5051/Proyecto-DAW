import './ProfileStats.css';
import ProfileStat from '@/app/atoms/ProfileStat/ProfileStat';

interface ProfileStatsProps {
    posts: number;
    followers: number;
    following: number;
    username: string;
}

/** Muestra las estadísticas del perfil. Seguidores y siguiendo son enlaces a sus listas. */
export default function ProfileStats({ posts, followers, following, username }: ProfileStatsProps) {
    return (
        <div className="profile-stats">
            <ProfileStat count={posts} label="Publicaciones" />
            <ProfileStat count={followers} label="Seguidores" href={`/profile/${username}/followers`} />
            <ProfileStat count={following} label="Siguiendo" href={`/profile/${username}/following`} />
        </div>
    );
}