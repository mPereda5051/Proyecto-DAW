import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8090', // Conexión directa al backend
});

// Interceptor para añadir el Token JWT
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default api;
