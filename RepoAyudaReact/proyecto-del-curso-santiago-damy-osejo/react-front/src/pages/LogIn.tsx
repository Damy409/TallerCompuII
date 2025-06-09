import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import instance from '../utils/axios';

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleLogin = async () => {
    try {
      const res = await instance.post('/auth/login', { email, password });
      const { token } = res.data;
      localStorage.setItem('token', token);
      alert('Inicio de sesión exitoso');
      navigate('/');
    } catch (error) {
      alert('Error al iniciar sesión. Verifica tus credenciales.');
      console.error(error);
    }
  };

  return (
    <div className="bg-white shadow-lg rounded-lg p-8 w-full max-w-md">
      <h2 className="text-2xl font-bold text-blue-700 mb-6 text-center">Iniciar Sesión</h2>
      <input
        type="email"
        value={email}
        onChange={e => setEmail(e.target.value)}
        placeholder="Correo electrónico"
        className="w-full p-3 mb-4 border border-blue-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
      />
      <input
        type="password"
        value={password}
        onChange={e => setPassword(e.target.value)}
        placeholder="Contraseña"
        className="w-full p-3 mb-6 border border-blue-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
      />
      <button
        onClick={handleLogin}
        className="w-full bg-blue-600 text-white py-3 rounded hover:bg-blue-700 transition"
      >
        Iniciar sesión
      </button>
      <p className="mt-4 text-sm text-center">
        ¿No tienes cuenta? <a href="/register" className="text-blue-600 hover:underline">Regístrate</a>
      </p>
    </div>
  );
}

export default Login;
