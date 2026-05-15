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

// Servicio que extraer el metadata de una foto. No guarda informacion en la base de datos
export const extractMetadata = async (photo: File) => {
    const bearerToken = getToken();

    if (!bearerToken) {
      throw new Error("UNAUTHORIZED");
    }

    const formData = new FormData();
    formData.append('file', photo);

    const response = await fetch('http://localhost:9090/images/extractMetadata', {
      method: 'POST',
      headers: {
        'Authorization': bearerToken.startsWith('Bearer ') ? bearerToken : `Bearer ${bearerToken}`, // Usamos el token de atuentificacion
      },
      body: formData,
    });

    if (!response.ok) {
      throw new Error("Error en la subida");
    }

    return response.json();
}

export const logout = () => {
  localStorage.removeItem('token');
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
