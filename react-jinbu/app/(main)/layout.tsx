import Navbar from "../organisms/Navbar";
import "./page.css";
import "../organisms/navbar.css";
import "../organisms/imageMenu.css";
import "../molecule/PhotoCardComponent/PhotoCard.css";
import "../atoms/AddButtonComponent/addButton.css";

export default function MainLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <>
      <Navbar />
      {children}
    </>
  );
}
