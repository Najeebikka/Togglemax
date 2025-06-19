// lib/api.js
export async function login(username, password) {
  const response = await fetch('http://localhost:8080/api/login', {
    method: 'GET',
    headers: {
      'Authorization': 'Basic ' + btoa(`${username}:${password}`),
    },
    credentials: 'include',
  });

  if (!response.ok) {
    throw new Error('Login failed');
  }

  return await response.text(); // or response.json() if your backend returns a body
}
