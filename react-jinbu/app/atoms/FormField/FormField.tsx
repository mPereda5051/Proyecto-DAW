export default function FormField({ radio = '' }) {
    return (
        <div className="search-container">
            <input
                type="text"
                placeholder="Buscar fotos..."
                className="search-input"
                style={{ borderRadius: radio }}
            />
        </div>
    );
}