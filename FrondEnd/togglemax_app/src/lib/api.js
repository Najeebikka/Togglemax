export async function login(email, password) {

  const baseURL = process.env.NEXT_PUBLIC_API_URL;
  
  const res = await fetch(`${baseURL}/api/auth/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ email, password }),
  });

  if (!res.ok) throw new Error("Invalid credentials");

  const { token } = await res.json();
  localStorage.setItem("token", token);
}

export function logout() {
  localStorage.removeItem("token");
}
