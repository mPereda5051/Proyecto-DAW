import { useState, useEffect } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import heroImg from './assets/hero.png'
import api from './api/axios'
import './App.css'

function App() {
  const [count, setCount] = useState(0)
  const [status, setStatus] = useState('Checking backend...')
  const [users, setUsers] = useState<any[]>([])
  const [photos, setPhotos] = useState<any[]>([])

  useEffect(() => {
    console.log("Intentando conectar con el backend...");
    
    // Obtener usuarios
    api.get('/user/all')
      .then((res) => {
        setStatus('Backend: Online ✓');
        setUsers(res.data);
      })
      .catch(() => setStatus('Backend: Error ✗'));

    // Obtener fotos
    api.get('/images/all')
      .then((res) => {
        setPhotos(res.data);
      })
      .catch((err) => console.error("Error al cargar fotos:", err));

  }, [])

  return (
    <>
      <section id="center">
        {/* ... (hero y status se mantienen) */}
        <div>
          <h1>Jinbu</h1>
          <p className="status-badge" style={{
            padding: '10px',
            borderRadius: '8px',
            backgroundColor: status.includes('✓') ? '#2e7d32' : '#c62828',
            color: 'white',
            fontWeight: 'bold',
            marginTop: '20px'
          }}>
            {status}
          </p>
        </div>

        {/* Panel de Usuarios */}
        <div className="users-grid">
          <h2 style={{width: '100%', textAlign: 'center'}}>Usuarios</h2>
          {users.map((user) => (
              <div key={user.id} className="user-card" style={{border: '1px solid #ccc', padding: '10px', margin: '5px', borderRadius: '8px', textAlign: 'left'}}>
                <div style={{display: 'flex', justifyContent: 'space-between', alignItems: 'center'}}>
                    <div>
                        <strong>{user.username}</strong><br/>
                        <span style={{fontSize: '0.8rem', color: '#666'}}>{user.email}</span>
                    </div>
                    <div style={{backgroundColor: '#e3f2fd', padding: '5px 10px', borderRadius: '15px', fontSize: '0.8rem', color: '#1976d2', fontWeight: 'bold'}}>
                        ID: {user.id} | Seguidores: {user.followersCount || 0}
                    </div>
                </div>
              </div>
          ))}
        </div>

        {/* Galería de Fotos (Nueva Funcionalidad) */}
        <div className="photos-grid" style={{marginTop: '40px', textAlign: 'center'}}>
          <h2>Galería de Fotos</h2>
          <div style={{display: 'flex', flexWrap: 'wrap', justifyContent: 'center', gap: '15px'}}>
            {photos.length > 0 ? photos.map((photo) => (
              <div key={photo.id} className="photo-item">
                <img src={photo.fullUrl} alt={photo.name} style={{width: '150px', height: '150px', objectFit: 'cover', borderRadius: '8px'}} />
                <p style={{fontSize: '0.8rem'}}>{photo.name}</p>
              </div>
            )) : <p>No hay fotos todavía.</p>}
          </div>
        </div>

        <button
          className="counter"
          onClick={() => setCount((count) => count + 1)}
          style={{ marginTop: '40px' }}
        >
          Count is {count}
        </button>
      </section>

      <div className="ticks"></div>

      <section id="next-steps">
        <div id="docs">
          <svg className="icon" role="presentation" aria-hidden="true">
            <use href="/icons.svg#documentation-icon"></use>
          </svg>
          <h2>Documentation</h2>
          <p>Your questions, answered</p>
          <ul>
            <li>
              <a href="https://vite.dev/" target="_blank">
                <img className="logo" src={viteLogo} alt="" />
                Explore Vite
              </a>
            </li>
            <li>
              <a href="https://react.dev/" target="_blank">
                <img className="button-icon" src={reactLogo} alt="" />
                Learn more
              </a>
            </li>
          </ul>
        </div>
        <div id="social">
          <svg className="icon" role="presentation" aria-hidden="true">
            <use href="/icons.svg#social-icon"></use>
          </svg>
          <h2>Connect with us</h2>
          <p>Join the Vite community</p>
          <ul>
            <li>
              <a href="https://github.com/vitejs/vite" target="_blank">
                <svg
                  className="button-icon"
                  role="presentation"
                  aria-hidden="true"
                >
                  <use href="/icons.svg#github-icon"></use>
                </svg>
                GitHub
              </a>
            </li>
            <li>
              <a href="https://chat.vite.dev/" target="_blank">
                <svg
                  className="button-icon"
                  role="presentation"
                  aria-hidden="true"
                >
                  <use href="/icons.svg#discord-icon"></use>
                </svg>
                Discord
              </a>
            </li>
            <li>
              <a href="https://x.com/vite_js" target="_blank">
                <svg
                  className="button-icon"
                  role="presentation"
                  aria-hidden="true"
                >
                  <use href="/icons.svg#x-icon"></use>
                </svg>
                X.com
              </a>
            </li>
            <li>
              <a href="https://bsky.app/profile/vite.dev" target="_blank">
                <svg
                  className="button-icon"
                  role="presentation"
                  aria-hidden="true"
                >
                  <use href="/icons.svg#bluesky-icon"></use>
                </svg>
                Bluesky
              </a>
            </li>
          </ul>
        </div>
      </section>

      <div className="ticks"></div>
      <section id="spacer"></section>
    </>
  )
}

export default App
