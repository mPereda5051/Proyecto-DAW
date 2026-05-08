import ProfileSection from "@/app/organisms/ProfileSection/ProfileSection";
import ProfileGrid from "@/app/organisms/ProfileGrid/ProfileGrid";
const UserTest = {
    avatarSrc: "https://i.pravatar.cc/150",
    avatarAlt: "User Avatar",
    username: "David_foto",
    bio: "Fotógrafo apasionado por capturar momentos únicos.",
    posts: 120,
    followers: 450,
    following: 180  
}

const photosTest = [
    { id: 1, title: "Foto 1", src: "https://picsum.photos/seed/1/300" },
      { id: 2, title: "Foto 2", src: "https://picsum.photos/seed/2/300" },
      { id: 3, title: "Foto 3", src: "https://picsum.photos/seed/3/300" },
      { id: 4, title: "Foto 4", src: "https://picsum.photos/seed/4/300" },
      { id: 5, title: "Foto 5", src: "https://picsum.photos/seed/5/300" },
      { id: 6, title: "Foto 6", src: "https://picsum.photos/seed/6/300" },
];

interface ProfilePageProps {
    params: {
        username: string;
    }
}

export default function ProfilePage({params}: ProfilePageProps) {
    return (
        <main>
        <ProfileSection
            avatarSrc={UserTest.avatarSrc}
            avatarAlt={UserTest.avatarAlt}
            username={params.username}
            bio={UserTest.bio}
            posts={UserTest.posts}
            followers={UserTest.followers}
            following={UserTest.following}
        />
        <ProfileGrid photos={photosTest} />
        </main>
    );
}
