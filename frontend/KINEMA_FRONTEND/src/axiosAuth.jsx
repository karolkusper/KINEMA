import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const api = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 10000, // Opcjonalnie: limit czasu oczekiwania na odpowiedź,
    withCredentials: true
});

// Add a request interceptor
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

api.interceptors.response.use(
  (response) => response,
  (error) => {
    const navigate = useNavigate();
    if (error.response && (error.response.status === 403 || error.response.status === 401)) {
      navigate('/error');
    }
    return Promise.reject(error);
  }
);

export default api