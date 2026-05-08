import './ProfileStat.css'
interface ProfileStatProps {
    count: number;
    label: string;
}

export default function ProfileStat({ count, label }: ProfileStatProps) {
    return (
        <div className="profile-Stat">
            <h3>{count}</h3>
            <p>{label}</p>
        </div>
    );
}