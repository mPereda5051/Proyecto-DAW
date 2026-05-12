"use client";
import styles from './uploadInformation.module.css';

import { useRouter } from 'next/navigation';
import FormField from '@/app/atoms/FormField/FormField';
import Button from '@/app/atoms/Button/Button';

import { Title, AddComment, Exposure, Iso, Camera } from '@mui/icons-material';

export default function UploadImageInformation() {
    const router = useRouter();

    const redirectionHandler = () => {
        alert("Redireccion");

        router.push('/')
    }

    return (
        <div className={styles.main}>
            <div className={styles.imagePanel}>
                <img
                    src="https://jinbu-s3-bucket.s3.us-east-1.amazonaws.com/2JPG" 
                    alt="Foto de detalle"
                />
            </div>
            <div className={styles.form}>
                <FormField placeholder='Title' Icon={Title} radio='10px' />
                <FormField placeholder='Message' Icon={AddComment} radio='10px' />
                <FormField placeholder='ISO' Icon={Iso} radio='10px' />
                <FormField placeholder='Aperture' Icon={Camera} radio='10px' />
                <FormField placeholder='Exposure' Icon={Exposure} radio='10px' />
                <Button label='Submit' onClick={() => redirectionHandler()} width='100%' />
            </div>
        </div>
    );
}