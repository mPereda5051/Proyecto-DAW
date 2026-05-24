import Input from "@/app/atoms/Input/Input";
import "@/app/atoms/Input/Input.css";
import "./formField.css";

interface FormFieldProps {
    label: string;
    type: string;
    placeholder: string;
    value: string;
    onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
    error?: string;
}



export default function FormField({label, type, placeholder, value, onChange, error }: FormFieldProps) {
    return (
        <div className="form-field">
            <label className="form-label">{label}</label>
            <Input
            type={type}
            placeholder={placeholder}
            value={value}
            onChange={onChange}
            />
            {error && <span className="form-error">{error}</span>}
            
        </div>
    );
}