'use client';

import { useEffect } from 'react';
import { useRouter } from 'next/navigation';
import { getCurrentUsername } from '@/app/services/authService';

export default function Cuenta() {
  const router = useRouter();

  useEffect(() => {
    const username = getCurrentUsername();
    router.replace(`/profile/${username}`);
  }, []);

  return null;
}
