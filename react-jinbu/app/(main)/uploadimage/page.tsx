"use client";
import React, { useState } from 'react';
import styles from './upload.module.css';
import Button from '@/app/atoms/Button/Button';
import { useRouter } from 'next/navigation';
import FormField from '@/app/atoms/FormField/FormField';
import { Title, AddComment, Exposure, Iso, Camera } from '@mui/icons-material';
// Models
import { Photo } from '@/app/services/models/photo';
import { Post } from '@/app/services/models/post';
// Servicios
import { extractMetadata } from '@/app/services/postService';
import { uploadPhotoAndPost } from '@/app/services/postService';

export default function UploadImage() {
    const router = useRouter();

    // Boolean que cambia cuando el usuario sube una foto
    const [metadata, setMetadata] = useState<any>(null);
    const [isPhotoNotUpload, setIsPhotoNotUpload] = useState(true);

    const [title, setTitle] = useState('');
    const [message, setMessage] = useState('');

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
            // Guardamos metadata en el useState
            setMetadata(await extractMetadata(imageFile));

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
        setMetadata(null);
    };

    const redirectionHandler = async () => {
        const photo: Photo = {
            name: '',
            iso: metadata.iso,
            aperture: metadata.aperture,
            exposure: metadata.exposure,
            width: metadata.width,
            height: metadata.height,
            extension: metadata.extension
        }

        const post: Post = {
            title: title,
            content: message
        }
        try {
            if (!imageFile) return; 

            await uploadPhotoAndPost(post, photo, imageFile);

            alert("Redireccion");
        } catch (error) {
            console.error("Error subiendo archivo: ", error);
            alert("error temporal")
        }
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
                            <Button label='Next' onClick={() => finishUpload()} width='150px' disabled={!isPhotoNotUpload} />
                        </div>
                    </>
                )}
            </div>

            <div className={styles.form}>
                <FormField 
                placeholder='Título' 
                Icon={Title} 
                radio='10px' 
                onChange={(e :React.ChangeEvent<HTMLInputElement>) => {
                    setTitle(e.target.value);
                }}
                />
                <FormField 
                placeholder='Mensaje' 
                Icon={AddComment} 
                radio='10px' 
                onChange={(e: React.ChangeEvent<HTMLInputElement>) => {
                    setMessage(e.target.value);
                }}
                />
                <FormField
                    placeholder='ISO'
                    Icon={Iso}
                    radio='10px'
                    disabled={isPhotoNotUpload}
                    value={metadata?.iso || ''}
                    onChange={(e: React.ChangeEvent<HTMLInputElement>) => {
                        setMetadata({
                            ...metadata,          
                            iso: e.target.value 
                        });
                    }}
                />
                <FormField
                    placeholder='Apertura'
                    Icon={Camera}
                    radio='10px'
                    disabled={isPhotoNotUpload}
                    value={metadata?.aperture || ''}
                    onChange={(e: React.ChangeEvent<HTMLInputElement>) => {
                        setMetadata({
                            ...metadata,          
                            aperture: e.target.value 
                        });
                    }}
                />
                <FormField
                    placeholder='Exposición'
                    Icon={Exposure}
                    radio='10px'
                    disabled={isPhotoNotUpload}
                    value={metadata?.exposure || ''}
                    onChange={(e: React.ChangeEvent<HTMLInputElement>) => {
                        setMetadata({
                            ...metadata,          
                            exposure: e.target.value 
                        });
                    }}
                />
                <Button label='Enviar' onClick={() => redirectionHandler()} width='100%' disabled={isPhotoNotUpload} />
            </div>
        </div>

    );
}