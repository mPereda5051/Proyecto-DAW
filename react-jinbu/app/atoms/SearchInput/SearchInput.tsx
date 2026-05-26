import SearchIcon from '@mui/icons-material/Search';
import "./searchInput.css";

interface SearchInputProps {
    type?: string;
    placeholder?: string;
    value?: string;
    radio?: string;
    disabled?: boolean;
    Icon?: React.ElementType;
    onChange?: (e: React.ChangeEvent<HTMLInputElement>) => void;
}

export default function SearchInput({
    type = "text",
    placeholder = "Buscar...",
    value,
    radio,
    disabled = false,
    Icon = SearchIcon,
    onChange }: SearchInputProps) {
    return (
        <div className="search-container">
            <Icon className="search-icon" />
            <input
                type={type}
                placeholder={placeholder}
                value={value}
                style={{ borderRadius: radio }}
                disabled={disabled}
                onChange={onChange}
                className="search-input"
            />
        </div>
    );
}
