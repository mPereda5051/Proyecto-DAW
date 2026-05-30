import { useState, useEffect, useRef } from 'react';
import SearchIcon from '@mui/icons-material/Search';
import "./searchInput.css";
import { searchUsers } from '@/app/services/userService';
import Link from 'next/link';
import { useRouter } from 'next/navigation';

interface SearchInputProps {
    type?: string;
    placeholder?: string;
    value?: string;
    radio?: string;
    disabled?: boolean;
    Icon?: React.ElementType;
}

export default function SearchInput({
    type = "text",
    placeholder = "Buscar usuarios...",
    radio,
    disabled = false,
    Icon = SearchIcon }: SearchInputProps) {
    
    const [query, setQuery] = useState("");
    const [results, setResults] = useState<any[]>([]);
    const [showDropdown, setShowDropdown] = useState(false);
    const dropdownRef = useRef<HTMLDivElement>(null);
    const router = useRouter();

    useEffect(() => {
        const handleClickOutside = (event: MouseEvent) => {
            if (dropdownRef.current && !dropdownRef.current.contains(event.target as Node)) {
                setShowDropdown(false);
            }
        };
        document.addEventListener("mousedown", handleClickOutside);
        return () => document.removeEventListener("mousedown", handleClickOutside);
    }, []);

    const handleSearch = async (val: string) => {
        setQuery(val);
        if (val.length > 0) {
            try {
                const data = await searchUsers(val);
                setResults(data);
                setShowDropdown(true);
            } catch (error) {
                console.error("Error buscando usuarios", error);
            }
        } else {
            setResults([]);
            setShowDropdown(false);
        }
    };

    const handleResultClick = (username: string) => {
        setQuery("");
        setShowDropdown(false);
        router.push(`/profile/${username}`);
    };

    return (
        <div className="search-container" ref={dropdownRef}>
            <Icon className="search-icon" />
            <input
                type={type}
                placeholder={placeholder}
                value={query}
                style={{ borderRadius: radio }}
                disabled={disabled}
                onChange={(e) => handleSearch(e.target.value)}
                onFocus={() => query.length > 0 && setShowDropdown(true)}
                className="search-input"
            />
            {showDropdown && (
                <div className="search-dropdown">
                    {results.length > 0 ? (
                        results.map((user) => (
                            <div 
                                key={user.username} 
                                className="search-result-item"
                                onClick={() => handleResultClick(user.username)}
                            >
                                <div className="search-result-avatar"></div>
                                <div className="search-result-info">
                                    <span className="search-result-username">@{user.username}</span>
                                    <span className="search-result-name">{user.name}</span>
                                </div>
                            </div>
                        ))
                    ) : (
                        <div className="search-no-results">No se encontraron usuarios</div>
                    )}
                </div>
            )}
        </div>
    );
}
