import './ProfileStats.css';
import ProfileStat from '@/app/atoms/ProfileStat/ProfileStat';

interface ProfileStatsProps {
    posts: number;
    followers: number;
    following: number;
}

export default function ProfileStats({ posts, followers, following }: ProfileStatsProps) {
    return (
        <div className="profile-stats">
            <ProfileStat count={posts} label="Posts" />
            <ProfileStat count={followers} label="Followers" />
            <ProfileStat count={following} label="Following" />
        </div>
    );
}