import { PhotoData } from "./PhotoData";
import './PhotoCard.css'

export default function PhotoCard(photoProps: PhotoData) {
    const photo = photoProps;
    return (
        <a href="#" className="link">
                <div className="imageContainer" key={photo.id}>
                    {photo.title && <h2>{photo.title}</h2>}
                    <div className="previewData">
                        <span className="iso">ISO 100</span>
                        <span> • </span>
                        <span className="aperture">F/2</span>
                    </div>
                    <img 
                        src={typeof photo.src === 'string' ? photo.src : photo.src.src}
                        alt={photo.title || "Photo"} 
                    />
                </div>
        </a>
    );
}