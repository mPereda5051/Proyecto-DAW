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

  useEffect(() => {
    console.log("Intentando conectar con el backend en /api/user/all...");
    api.get('/user/all')
      .then((res) => {
        console.log("Datos recibidos del backend:", res.data);
        setStatus('Backend: Online ✓');
        // Si el backend responde, usamos esos datos (aunque sea una lista vacía [])
        setUsers(res.data);
      })
      .catch((err) => {
        console.error("Error al conectar con el backend:", err);
        setStatus('Backend: Error ✗');
        
        // Solo mostramos ejemplos si realmente no hay conexión (servidor apagado)
        setUsers([
          { id: 1, username: 'Ejemplo_Admin', email: 'admin@ejemplo.com' },
          { id: 2, username: 'Ejemplo_User', email: 'user@ejemplo.com' }
        ]);
      });
  }, [])

  return (
    <>
      <section id="center">
        <div className="hero">
          <img src={heroImg} className="base" width="170" height="179" alt="" />
          <img src={reactLogo} className="framework" alt="React logo" />
          <img src={viteLogo} className="vite" alt="Vite logo" />
        </div>
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
          {users.map((user) => (
            <div key={user.id} className="user-card">
              <div className="user-avatar">
                {user.username.charAt(0).toUpperCase()}
              </div>
              <div className="user-info">
                <h3>{user.username}</h3>
                <p>{user.email}</p>
              </div>
            </div>
          ))}
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
