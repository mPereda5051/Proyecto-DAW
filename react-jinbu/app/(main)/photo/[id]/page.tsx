import "./photoDetail.css";
import SendIcon from '@mui/icons-material/Send';
import LikeButton from "@/app/atoms/LikeButton/LikeButton";

// Aqui se llamaria a la API para obtener la foto
export default async function PhotoDetailPage(props: { params: Promise<{ id: string }> }) {
    const params = await props.params;
    const { id } = params;

    return (
        <div className="photo-detail-container">
            {/* Panel Izquierdo: La Imagen */}
            <div className="image-panel">
                <img 
                    src="https://jinbu-s3-bucket.s3.us-east-1.amazonaws.com/2JPG" //Placeholder
                    alt="Foto de detalle" 
                />
            </div>

            {/* Panel Derecho: Información y Comentarios */}
            <div className="info-panel">
                <header className="info-header">
                    <h1>Título de la foto {id}</h1>
                    <div className="metadata">
                        <span>ISO 100</span>
                        <span>F/11</span>
                        <span>1/200s</span>
                    </div>
                </header>
                    {/* Comentarios de ejemplo para visualizar como se veria*/}

                <main className="comments-section">
                    <div className="comment">
                        <span className="comment-user">usuario_123</span>
                        <span className="comment-text">dios mio que es eso?!!</span>
                    </div>
                    <div className="comment">
                        <span className="comment-user">NoobMaster69</span>
                        <span className="comment-text">e visto mejores</span>
                    </div>
                </main>

                <LikeButton initialCount={42} />
                <footer className="comment-input-section">
                    <input 
                        type="text" 
                        placeholder="Añade un comentario..." 
                        className="comment-input"
                    />
                    <button className="post-button">
                        <SendIcon />
                    </button>
                </footer>
            </div>
        </div>
    );
}
