import { StaticImageData } from "next/image";

export interface PhotoData {
    id: number,
    title: string,
    src: string | StaticImageData,
    likedByUser?: boolean,
    likes?: number
}