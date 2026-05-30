import { StaticImageData } from "next/image";

export interface PhotoData {
    id: number,
    title: string,
    src: string | StaticImageData,
    iso?: number,
    aperture?: number,
    likedByUser?: boolean,
    likes?: number,
    showDelete?: boolean,
    username?: string
}