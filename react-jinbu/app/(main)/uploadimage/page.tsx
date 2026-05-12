"use client";
import React, { useState } from 'react';
import './upload.css';
import Button from '@/app/atoms/Button/Button';
import { useRouter } from 'next/navigation';

export default function UploadImage() {
    const router = useRouter();

    const finishUpload = () => {
        router.push('/uploadimageinformation')
    }

    const [image, setImage] = useState<string | null>(null);
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
            reader.onload = () => {
                setImage(reader.result as string);
            };
            reader.readAsDataURL(file);
        }
    };

    return (
        <div className="upload-container">
            <h1 className="upload-title"> Upload your image</h1>

            <div
                className={`droppable-area ${isDragging ? 'is-over' : ''} ${image ? 'has-image' : ''}`}
                onDragOver={handleDragOver}
                onDrop={handleDrop}
            >
                {image ? (
                    <img src={image} alt="Preview" className="preview-img" />
                ) : (
                    <div className="upload-placeholder">
                        <span style={{ fontSize: '3rem' }}>📁</span>
                        <p>Drag and drop your photo here</p>
                    </div>
                )}
            </div>

            {image && (
                <>
                    <div className="dragDropButtons">
                        <Button label='Remove Image' onClick={() => setImage(null)} width='150px' />
                        <Button label='Next' onClick={() => finishUpload()} width='150px' />
                    </div>
                </>
            )}
        </div>

    );
}