//servicio que realiza llamada al backend

export const login = async (username: string, password: string) => {
  const response = await fetch('http://localhost:9090/authenticate', {
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
  }

  return response;
};

export const logout = () => {
  localStorage.removeItem('token');
};

export const getToken = () => {
  return localStorage.getItem('token');
};
