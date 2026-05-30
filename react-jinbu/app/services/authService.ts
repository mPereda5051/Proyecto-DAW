import { BASE_URL } from './config';
import Cookies from 'js-cookie';

//servicio que realiza llamada al backend
export const login = async (username: string, password: string) => {
  const response = await fetch(`${BASE_URL}/authenticate`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ username, password }),
  });

  if (!response.ok) {
    throw new Error('Error en la autenticación');
  }

  const token = response.headers.get('Authorization');
  if (token) {
    localStorage.setItem('token', token);
    Cookies.set('token', token, { expires: 1 });
  }

  return response;
};

export const register = async (name: string, username: string, email: string, password: string) => {
  const response = await fetch(`${BASE_URL}/user/register`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ name, username, email, password }),
  });

  if (!response.ok) throw new Error('Error en el registro');
  return response;
};

export const logout = () => {
  localStorage.removeItem('token');
  Cookies.remove('token');
};

export const getToken = () => {
  return localStorage.getItem('token');
};

export const getCurrentUsername = () => {
  const token = getToken();
  if (!token) return null;
  
  try {
    const base64Url = token.includes(' ') ? token.split(' ')[1].split('.')[1] : token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload).sub;
  } catch (e) {
    return null;
  }
};
