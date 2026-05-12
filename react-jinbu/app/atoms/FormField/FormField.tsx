import SearchIcon from '@mui/icons-material/Search';
import "./formField.css";

interface InputProps {
    type?: string;
    placeholder?: string;
    value?: string;
    radio?: string;
    Icon?: React.ElementType;
    onChange?: (e: React.ChangeEvent<HTMLInputElement>) => void;
}



export default function FormField({
    type = "text", 
    placeholder = "Buscar...", 
    value, 
    radio, 
    Icon = SearchIcon, 
    
    onChange }: InputProps) {
        return (
        <div className="search-container">
            <Icon className="search-icon" />
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