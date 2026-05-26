'use client';

import { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';
import { getToken } from '../services/authService';
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
  const router = useRouter();
  const [isAuthorized, setIsAuthorized] = useState(false);

  useEffect(() => {
    const token = getToken();
    if (!token) {
      router.push('/login');
    } else {
      setIsAuthorized(true);
    }
  }, [router]);

  if (!isAuthorized) {
    return null; 
  }

  return (
    <>
      <Navbar />
      {children}
    </>
  );
}
