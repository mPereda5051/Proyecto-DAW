import AddIcon from '@mui/icons-material/Add';
import './addButton.css'

export default function AddButton() {
    return (
        <button id="addPhoto">
            <p id="addPhotoText">Add photo</p>
            <span id="addIcon"><AddIcon /></span>
        </button>
    );
}