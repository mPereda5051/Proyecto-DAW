"use client";
import React, { useState } from 'react';
import './upload.css';
import Button from '@/app/atoms/Button/Button';
import { useRouter } from 'next/navigation';
import { extractMetadata } from '@/app/services/authService';

export default function UploadImage() {
    const router = useRouter();

    const [imagePreview, setImagePreview] = useState<string | null>(null);
    const [imageFile, setImage] = useState<File | null>(null);
    const [isDragging, setIsDragging] = useState(false);

    const handleDragOver = (e: React.DragEvent) => {
        e.preventDefault();
        setIsDragging(true);
    };

    const handleDrop = (e: React.DragEvent) => {
        e.preventDefault();
        setIsDragging(false);

        const file = e.dataTransfer.files[0];
        if (file && file.type.startsWith('image/')) {
            const reader = new FileReader();
            setImage(file);
            reader.onload = () => {
                setImagePreview(reader.result as string)
            }
            reader.readAsDataURL(file);
        }
    };

    const finishUpload = async () => {
        if (!imageFile) return; // Meter mensaje de error para informar al usuario

        try {
            const metadata = await extractMetadata(imageFile);

            // Se guarda la imagen en el localStorage 
            localStorage.setItem('metadata', JSON.stringify(metadata));

            router.push('/uploadimageinformation')
        } catch (error) {
            // Meter mensaje de error (No se ha podido subir los datos o algo asi)
            console.log("Error mensaje: ", error)
        }
    }

    const removeImage = () => {
        setImage(null);
        setImagePreview(null);
    };

    return (
        <div className="upload-container">
            <h1 className="upload-title"> Upload your image</h1>

            <div
                className={`droppable-area ${isDragging ? 'is-over' : ''} ${imagePreview ? 'has-image' : ''}`}
                onDragOver={handleDragOver}
                onDrop={handleDrop}
            >
                {imagePreview ? (
                    <img src={imagePreview} alt="Preview" className="preview-img" />
                ) : (
                    <div className="upload-placeholder">
                        <span style={{ fontSize: '3rem' }}>📁</span>
                        <p>Drag and drop your photo here</p>
                    </div>
                )}
            </div>

            {imagePreview && (
                <>
                    <div className="dragDropButtons">
                        <Button label='Remove Image' onClick={() => removeImage()} width='150px' />
                        <Button label='Next' onClick={() => finishUpload()} width='150px' />
                    </div>
                </>
            )}
        </div>

    );
}