import axios from 'axios';

// L-port dyal Spring Boot li derti
const API_URL = 'http://localhost:4333/api/v1'; 

export const apiClient = axios.create({
    baseURL: API_URL,
    headers: {
        'Content-Type': 'application/json',
    },
});

// Interceptor: 9bel ma t-sifet ay request, chouf wach kayn token f localStorage w zido
apiClient.interceptors.request.use((config) => {
    if (typeof window !== 'undefined') {
        const token = localStorage.getItem('token');
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
    }
    return config;
}, (error) => {
    return Promise.reject(error);
});