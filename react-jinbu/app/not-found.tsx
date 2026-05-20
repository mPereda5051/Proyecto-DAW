import Link from 'next/link';
import './not-found.css';

export default function NotFound() {
    return (
        <div className="not-found-container">
            <div className="not-found-content">
                <h1 className="not-found-code">404</h1>
                <h2 className="not-found-title">¡Ups! Página no encontrada</h2>
                <p className="not-found-text">
                    La fotografía que buscas parece haberse esfumado. 
                    Tal vez el enlace es incorrecto o la página ha sido movida.
                </p>
                <Link href="/" className="not-found-button">
                    Volver al inicio
                </Link>
            </div>
        </div>
    );
}
