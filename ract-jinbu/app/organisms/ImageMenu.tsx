import './imageMenu.css';

import PhotoCard from '../molecule/PhotoCardComponent/PhotoCard';

import img1 from './testimg/horizontal/kanenori-train-6907884.jpg';
import img2 from './testimg/horizontal/soultrain-sunset-8170058.jpg';
import img3 from './testimg/Vertical/miezekieze-cat-7319151.jpg';
import img4 from './testimg/horizontal/wal_172619-sea-7394353.jpg';
import img5 from './testimg/Vertical/elcodigodebarras-bridge-madrid-structure-254656.jpg';
import img6 from './testimg/horizontal/michael_pointner-metropolis-8800792.jpg';
import img7 from './testimg/Vertical/juanlufer4-madrid-2179925.jpg';
import img8 from './testimg/horizontal/liggraphy-hamburg-3071437.jpg';
import img9 from './testimg/Vertical/yeyalpha-city-5644601.jpg';
import img10 from './testimg/Vertical/pixelika-roof-1236342.jpg';
import img11 from './testimg/horizontal/wal_172619-city-9599967.jpg';
import img12 from './testimg/horizontal/chloe_dupre-city-7996136.jpg';
import img13 from './testimg/Vertical/ruslansikunov-flowers-8516916.jpg';
import { StaticImageData } from 'next/image';

interface PhotoItem {
    id: number;
    title: string;
    src: string | StaticImageData;
    iso: string;
    f: string;
}

export default function ImageMenu() {
    const photos: PhotoItem[] = [
        { id: 1, title: "Test titulo", src: img1, iso: "100", f: "11" },
        { id: 2, title: "Mi primera foto", src:"https://jinbu-s3-bucket.s3.us-east-1.amazonaws.com/2JPG", iso: "100", f: "11" },
        { id: 3, title: "", src: img2, iso: "100", f: "11" },
        { id: 4, title: "", src: img3, iso: "100", f: "11" },
        { id: 5, title: "", src: img4, iso: "100", f: "11" },
        { id: 6, title: "", src: img5, iso: "100", f: "11" },
        { id: 7, title: "", src: img6, iso: "100", f: "11" },
        { id: 8, title: "", src: img7, iso: "100", f: "11" },
        { id: 9, title: "", src: img8, iso: "100", f: "11" },
        { id: 10, title: "", src: img9, iso: "100", f: "11" },
        { id: 11, title: "", src: img10, iso: "100", f: "11" },
        { id: 12, title: "", src: img11, iso: "100", f: "11" },
        { id: 13, title: "", src: img12, iso: "100", f: "11" },
        { id: 14, title: "", src: img13, iso: "100", f: "11" },
    ];

    return (
        <main className="main">
            {photos.map((photo) => (
                <PhotoCard id={photo.id} src={typeof photo.src === 'string' ? photo.src : photo.src.src} title={photo.title} key={photo.id} />
            ))}
        </main>
    );
}