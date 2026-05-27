'use client';
import React, { useState, useRef } from 'react';
import styles from './upload.module.css';
import Button from '@/app/atoms/Button/Button';
import { useRouter } from 'next/navigation';
import SearchInput from '@/app/atoms/SearchInput/SearchInput';
import { Title, AddComment, Exposure, Iso, Camera } from '@mui/icons-material';
import { useSnackbar } from 'notistack';
// Models
import { Photo } from '@/app/services/models/photo';
import { Post } from '@/app/services/models/post';
// Servicios
import { extractMetadata } from '@/app/services/postService';
import { uploadPhotoAndPost } from '@/app/services/postService';

export default function UploadImage() {
    const { enqueueSnackbar } = useSnackbar();
    const router = useRouter();

    // Boolean que cambia cuando el usuario sube una foto
    const [metadata, setMetadata] = useState<any>(null);
    const [isPhotoNotUpload, setIsPhotoNotUpload] = useState(true);

    const [title, setTitle] = useState('');
    const [message, setMessage] = useState('');

    const [imagePreview, setImagePreview] = useState<string | null>(null);
    const [imageFile, setImage] = useState<File | null>(null);
    const [isDragging, setIsDragging] = useState(false);

    // Referencia para el input del archivo a traves del boton
    const fileInputRef = useRef<HTMLInputElement>(null);

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

    // Evento para boton dragandrop
    const handleFileInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const file = e.target.files?.[0];
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
        if (!imageFile) {
            enqueueSnackbar("Por favor, selecciona una imagen primero.", { variant: 'warning' });
            return;
        }

        try {
            // Guardamos metadata en el useState
            setMetadata(await extractMetadata(imageFile));

            // Desbloqueamos los campos de ISO, Aperture, Exposure y Submit
            setIsPhotoNotUpload(false);
            enqueueSnackbar("Metadatos extraídos correctamente", { variant: 'info' });
        } catch (error) {
            console.error("Error extrayendo metadatos: ", error);
            enqueueSnackbar("No se han podido extraer los metadatos", { variant: 'error' });
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
            enqueueSnackbar("Imagen subida con éxito", { variant: 'success' });
            router.push("/")
        } catch (error) {
            console.error("Error subiendo archivo: ", error);
            enqueueSnackbar("Error al subir la imagen. Inténtalo de nuevo.", { variant: 'error' });
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
                            <Button label="Seleccionar archivo" onClick={() => fileInputRef.current?.click()} width="200px" />
                            <input
                                type="file"
                                ref={fileInputRef}
                                onChange={handleFileInputChange}
                                accept="image/*"
                                style={{ display: 'none' }}
                            />
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
                <SearchInput
                placeholder='Título' 
                Icon={Title} 
                radio='10px' 
                onChange={(e :React.ChangeEvent<HTMLInputElement>) => {
                    setTitle(e.target.value);
                }}
                />
                <SearchInput
                placeholder='Mensaje' 
                Icon={AddComment} 
                radio='10px' 
                onChange={(e: React.ChangeEvent<HTMLInputElement>) => {
                    setMessage(e.target.value);
                }}
                />
                <SearchInput
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
                <SearchInput
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
                <SearchInput
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