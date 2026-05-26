'use client';

import React, { useState } from 'react';
import { CacheProvider } from '@emotion/react';
import { SnackbarProvider } from 'notistack';
import createEmotionCache from './createEmotionCache';

/**
 * Registro de Emotion para Next.js App Router (MUI)
 */
export default function EmotionRegistry({ children }: { children: React.ReactNode }) {
  const [cache] = useState(() => createEmotionCache());
  
  return (
    <CacheProvider value={cache}>
      <SnackbarProvider 
        maxSnack={3} 
        autoHideDuration={3000} 
        anchorOrigin={{ vertical: 'top', horizontal: 'center' }}
      >
        {children}
      </SnackbarProvider>
    </CacheProvider>
  );
}
