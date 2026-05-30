import { useState, useEffect, useRef } from 'react';
import SearchIcon from '@mui/icons-material/Search';
import "./searchInput.css";
import { searchUsers, getProfilePictureUrl } from '@/app/services/userService';
import Link from 'next/link';
import { useRouter } from 'next/navigation';

interface SearchInputProps {
    type?: string;
    placeholder?: string;
    value?: string;
    radio?: string;
    disabled?: boolean;
    Icon?: React.ElementType;
    enableSearch?: boolean;
    onChange?: (e: React.ChangeEvent<HTMLInputElement>) => void;
}

export default function SearchInput({
    type = "text",
    placeholder = "Buscar usuarios...",
    value,
    radio,
    disabled = false,
    Icon = SearchIcon,
    enableSearch = false,
    onChange }: SearchInputProps) {
    
    const [query, setQuery] = useState(value || "");
    const [results, setResults] = useState<any[]>([]);
    const [showDropdown, setShowDropdown] = useState(false);
    const dropdownRef = useRef<HTMLDivElement>(null);
    const router = useRouter();

    useEffect(() => {
        if (value !== undefined) setQuery(value);
    }, [value]);

    useEffect(() => {
        if (!enableSearch) return;
        const handleClickOutside = (event: MouseEvent) => {
            if (dropdownRef.current && !dropdownRef.current.contains(event.target as Node)) {
                setShowDropdown(false);
            }
        };
        document.addEventListener("mousedown", handleClickOutside);
        return () => document.removeEventListener("mousedown", handleClickOutside);
    }, [enableSearch]);

    const handleInputChange = (val: string) => {
        setQuery(val);
        
        if (onChange) {
            const event = { target: { value: val } } as React.ChangeEvent<HTMLInputElement>;
            onChange(event);
        }

        if (enableSearch && val.length > 0) {
            triggerSearch(val);
        } else {
            setResults([]);
            setShowDropdown(false);
        }
    };

    const triggerSearch = async (val: string) => {
        try {
            const data = await searchUsers(val);
            
            const resultsWithAvatars = await Promise.all(
                data.map(async (user: any) => {
                    try {
                        const s3AvatarUrl = await getProfilePictureUrl(user.username);
                        if (s3AvatarUrl && typeof s3AvatarUrl === 'string' && s3AvatarUrl.startsWith('http')) {
                            return { ...user, avatarUrl: s3AvatarUrl };
                        }
                        return { ...user, avatarUrl: '/images/user.jpg' };
                    } catch (e) {
                        return { ...user, avatarUrl: '/images/user.jpg' };
                    }
                })
            );

            setResults(resultsWithAvatars);
            setShowDropdown(true);
        } catch (error) {
            console.error("Error buscando usuarios", error);
        }
    };

    const handleResultClick = (username: string) => {
        if (!enableSearch) return;
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
                onChange={(e) => handleInputChange(e.target.value)}
                onFocus={() => enableSearch && query.length > 0 && setShowDropdown(true)}
                className="search-input"
            />
            {enableSearch && showDropdown && (
                <div className="search-dropdown">
                    {results.length > 0 ? (
                        results.map((user) => (
                            <div 
                                key={user.username} 
                                className="search-result-item"
                                onClick={() => handleResultClick(user.username)}
                            >
                                <div className="search-result-avatar">
                                    <img 
                                        src={user.avatarUrl} 
                                        alt={user.username}
                                        style={{ width: '100%', height: '100%', borderRadius: '50%', objectFit: 'cover' }}
                                        crossOrigin="anonymous"
                                    />
                                </div>
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