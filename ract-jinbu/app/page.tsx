import MenuIcon from '@mui/icons-material/Menu';
import AddButton from './atoms/AddButtonComponent/AddButton';

import './page.css';
import ImageMenu from './organisms/ImageMenu';

export default function Home() {
  return (
    <>
      <div className="menu">
        <div className="menu">
          <div className="menu-logo">
            <MenuIcon />
            <p id="logo">Jinbu</p>
          </div>
          <div className="menu-item">
            <AddButton />
            <p>Profile photo</p>
          </div>
        </div>
      </div>
      <div id="content">
        <ImageMenu></ImageMenu>
      </div>
    </>
  );
}
