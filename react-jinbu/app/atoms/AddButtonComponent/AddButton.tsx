import AddIcon from '@mui/icons-material/Add';
import { useRouter } from 'next/navigation';

export default function AddButton() {
    const router = useRouter();

    const imageUploadPage =() => {
        router.push('/uploadimage')
    }

    return (
        <button id="addPhoto" onClick={() => imageUploadPage()}>
            <p id="addPhotoText">Subir foto</p>
            <span id="addIcon"><AddIcon /></span>
        </button>
    );
}