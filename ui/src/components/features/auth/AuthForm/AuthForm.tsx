'use client'; // Hit fih les hooks (useState, useRouter)

import React, { useState } from 'react';
import { useRouter } from 'next/navigation';
import { Input } from '@/components/common/Input/Input';
import { authService } from '@/services/api/authService';
import styles from './AuthForm.module.css';

export const AuthForm = () => {
    const router = useRouter();
    const [isLogin, setIsLogin] = useState(true);
    const [formData, setFormData] = useState({ username: '', email: '', password: '' });
    const [error, setError] = useState('');

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setError('');
        
        try {
            let res;
            if (isLogin) {
                res = await authService.login({ email: formData.email, password: formData.password });
            } else {
                res = await authService.register(formData);
            }
            
            // N-saviw token f l-khabya (localStorage)
            localStorage.setItem('token', res.token);
            
            // N-tiyroh l-dashboard
            router.push('/dashboard');
        } catch (err: any) {
            setError('Chi haja machi hya hadik, t2ked mn l-ma3loumat dyalek.');
        }
    };

    return (
        <div className={`glass-panel ${styles.formContainer}`}>
            <h2 className={styles.title}>{isLogin ? 'Mar7ba Bik' : 'Creyi Compte'}</h2>
            
            {error && <p className={styles.error}>{error}</p>}
            
            <form className={styles.form} onSubmit={handleSubmit}>
                {!isLogin && (
                    <Input 
                        label="Username" 
                        type="text" 
                        placeholder="waeloujdi" 
                        value={formData.username}
                        onChange={(e) => setFormData({...formData, username: e.target.value})}
                        required 
                    />
                )}
                
                <Input 
                    label="Email" 
                    type="email" 
                    placeholder="test@mail.com" 
                    value={formData.email}
                    onChange={(e) => setFormData({...formData, email: e.target.value})}
                    required 
                />
                
                <Input 
                    label="Password" 
                    type="password" 
                    placeholder="••••••••" 
                    value={formData.password}
                    onChange={(e) => setFormData({...formData, password: e.target.value})}
                    required 
                />
                
                <button type="submit" className={styles.submitBtn}>
                    {isLogin ? 'Dkhol (Login)' : 'T-sayyed (Register)'}
                </button>
            </form>

            <p className={styles.toggleText} onClick={() => setIsLogin(!isLogin)}>
                {isLogin ? 'Ma3endekch compte? ' : '3endek compte? '}
                <span>{isLogin ? 'T-sayyed' : 'Dkhol'}</span>
            </p>
        </div>
    );
};