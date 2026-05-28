"use client";
import { useEffect, useState, use } from "react";
import ProfileSection from "@/app/organisms/ProfileSection/ProfileSection";
import ProfileGrid from "@/app/organisms/ProfileGrid/ProfileGrid";
import { getUserProfile, getUserPosts } from "@/app/services/userService";
import { getCurrentUsername } from "@/app/services/authService";
import ProfileGridv2 from "@/app/organisms/ProfileGrid/ProfileGridv2";

interface ProfilePageProps {
    params: Promise<{
        username: string;
    }>
}

export default function ProfilePage({ params }: ProfilePageProps) {
    const { username } = use(params);
    const [user, setUser] = useState<any>(null);
    const [photos, setPhotos] = useState<any[]>([]);
    const [loading, setLoading] = useState(true);

    const loggedUser = getCurrentUsername();
    const isOwnProfile = loggedUser === username;

    useEffect(() => {
        const loadData = async () => {
            try {
                const [profileData, postsData] = await Promise.all([
                    getUserProfile(username),
                    getUserPosts(username)
                ]);
                
                setUser(profileData);
                setPhotos(postsData);
            } catch (error) {
                console.error("Error cargando el perfil:", error);
            } finally {
                setLoading(false);
            }
        };

        loadData();
    }, [username]);

    if (loading) return <div style={{ padding: "2rem", textAlign: "center" }}>Cargando perfil...</div>;
    if (!user) return <div style={{ padding: "2rem", textAlign: "center" }}>Usuario no encontrado</div>;

    return (
        <main>
            <ProfileSection
                avatarSrc={user.avatarUrl || "https://i.pravatar.cc/150"} 
                avatarAlt={user.username}
                username={user.username}
                bio={user.bio || "Sin biografía"}
                posts={photos.length}
                followers={user.followersCount || 0}
                following={user.followingCount || 0}
            />
            <ProfileGridv2
                userId={user.id}
                showDelete={isOwnProfile}
            />
        </main>
    );
}
