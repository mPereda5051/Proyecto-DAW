"use client";
import React, { useState } from 'react';
import styles from './upload.module.css';
import Button from '@/app/atoms/Button/Button';
import { useRouter } from 'next/navigation';
import { extractMetadata } from '@/app/services/authService';
import FormField from '@/app/atoms/FormField/FormField';

import { Title, AddComment, Exposure, Iso, Camera } from '@mui/icons-material';

export default function UploadImage() {
    const router = useRouter();

     // Boolean que cambia cuando el usuario sube una foto
    const [isPhotoNotUpload, setIsPhotoNotUpload] = useState(true);

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

            // Desbloqueamos los campos de ISO, Aperture, Exposure y Submit
            setIsPhotoNotUpload(false);
        } catch (error) {
            // Meter mensaje de error (No se ha podido subir los datos o algo asi)
            console.log("Error mensaje: ", error)
        }
    }

    const removeImage = () => {
        setImage(null);
        setImagePreview(null);
        setIsPhotoNotUpload(true);
    };

    const redirectionHandler = () => {
        alert("Redireccion");

        router.push('/')
    }

    return (
        <div className={styles.main}>
            <div className={styles.uploadContainer}>
                <h1 className={styles.uploadTitle}> Sube tu fotografía</h1>

                <div
                    className={`
                        ${styles.droppableArea} 
                        ${isDragging ? styles.isOver : ''} 
                        ${imagePreview ? styles.hasImage : ''}
                    `}
                    onDragOver={handleDragOver}
                    onDrop={handleDrop}
                >
                    {imagePreview ? (
                        <img src={imagePreview} alt="Preview" className={styles.previewImg} />
                    ) : (
                        <div className={styles.uploadPlaceholder}>
                            <span style={{ fontSize: '3rem', color: 'white' }}>Button for opening files</span>
                        </div>
                    )}
                </div>

                {imagePreview && (
                    <>
                        <div className={styles.dragDropButtons}>
                            <Button label='Remove Image' onClick={() => removeImage()} width='150px' />
                            <Button label='Next' onClick={() => finishUpload()} width='150px' />
                        </div>
                    </>
                )}
            </div>


            <div className={styles.form}>
                <FormField placeholder='Título' Icon={Title} radio='10px' />
                <FormField placeholder='Mensaje' Icon={AddComment} radio='10px' />
                <FormField placeholder='ISO' Icon={Iso} radio='10px' disabled={isPhotoNotUpload} />
                <FormField placeholder='Apertura' Icon={Camera} radio='10px' disabled={isPhotoNotUpload}/>
                <FormField placeholder='Exposición' Icon={Exposure} radio='10px' disabled={isPhotoNotUpload}/>
                <Button label='Enviar' onClick={() => redirectionHandler()} width='100%' disabled={isPhotoNotUpload} />
            </div>
        </div>

    );
}