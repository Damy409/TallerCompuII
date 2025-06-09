import axios from 'axios';
const apiUrl = import.meta.env.VITE_API_URL;

const instance = axios.create({
  baseURL: apiUrl,
  withCredentials: false,
});

instance.interceptors.request.use(config => {
  const token = localStorage.getItem('jwt');
  if (token) {
    config.headers['Authorization'] = `Bearer ${token}`;
  }
  return config;
});

export default instance;
