export async function login(email, password) {
  
  const res = await fetch("http://localhost:8080/api/auth/login", {
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
