'use client';

import { CacheProvider } from '@emotion/react';
import createEmotionCache from './createEmotionCache';

export default function EmotionRegistry({ children }: { children: React.ReactNode }) {
  const cache = createEmotionCache();
  return <CacheProvider value={cache}>{children}</CacheProvider>;
}
