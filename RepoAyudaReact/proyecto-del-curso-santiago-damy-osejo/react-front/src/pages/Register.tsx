import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import instance from '../utils/axios';

function Register() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [name, setName] = useState('');
  const navigate = useNavigate();

  const handleRegister = async () => {
    try {
      await instance.post('/auth/register', { email, password, name });
      alert('Registro exitoso. Ahora puedes iniciar sesión.');
      navigate('/login');
    } catch (error) {
      alert('Error al registrarse. Intenta con otro correo.');
      console.error(error);
    }
  };

  return (
    <div className="bg-white shadow-lg rounded-lg p-8 w-full max-w-md">
      <h2 className="text-2xl font-bold text-blue-700 mb-6 text-center">Registro</h2>
      <input
        type="text"
        value={name}
        onChange={e => setName(e.target.value)}
        placeholder="Nombre"
        className="w-full p-3 mb-4 border border-blue-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
      />
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
        onClick={handleRegister}
        className="w-full bg-blue-600 text-white py-3 rounded hover:bg-blue-700 transition"
      >
        Registrarse
      </button>
      <p className="mt-4 text-sm text-center">
        ¿Ya tienes cuenta? <a href="/login" className="text-blue-600 hover:underline">Inicia sesión</a>
      </p>
    </div>
  );
}

export default Register;
