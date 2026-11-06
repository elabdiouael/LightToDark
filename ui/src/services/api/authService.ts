import { apiClient } from './axiosConfig';

export const authService = {
    login: async (data: any) => {
        const response = await apiClient.post('/auth/login', data);
        return response.data;
    },
    register: async (data: any) => {
        const response = await apiClient.post('/auth/register', data);
        return response.data;
    }
};