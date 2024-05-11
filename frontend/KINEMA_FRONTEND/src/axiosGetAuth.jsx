import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080', // Adres URL Twojego backendu
  timeout: 10000, // Opcjonalnie: limit czasu oczekiwania na odpowiedź
  // Opcjonalnie: dodatkne ustawienia
});

export default axiosInstance;