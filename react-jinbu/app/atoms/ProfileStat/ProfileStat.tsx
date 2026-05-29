import './ProfileStat.css'
import Link from 'next/link';

interface ProfileStatProps {
    count: number;
    label: string;
    href?: string;
}

export default function ProfileStat({ count, label, href }: ProfileStatProps) {
    const content = (
        <>
            <h3>{count}</h3>
            <p>{label}</p>
        </>
    );

    if (href) {
        return (
            <Link href={href} className="profile-Stat profile-Stat--link">
                {content}
            </Link>
        );
    }

    return (
        <div className="profile-Stat">
            {content}
        </div>
    );
}