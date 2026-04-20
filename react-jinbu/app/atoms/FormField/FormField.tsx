import "./formField.css";

interface InputProps {
    type?: string;
    placeholder?: string;
    value?: string;
    radio?: string;
    onChange?: (e: React.ChangeEvent<HTMLInputElement>) => void;
}



export default function FormField({type = "text", placeholder = "Buscar...", value, radio, onChange }: InputProps) {
    return (
        <div className="search-container">
            <input
                type={type}
                placeholder={placeholder}
                value={value}
                style={{ borderRadius: radio }}
                onChange={onChange}
                className="search-input"
                         
            />
        </div>
    );
}