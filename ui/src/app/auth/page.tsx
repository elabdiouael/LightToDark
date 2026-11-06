import React from 'react';
import { AuthForm } from '@/components/features/auth/AuthForm/AuthForm';
import { FadeIn } from '@/components/animations/FadeIn/FadeIn';

// Bima anana mabghinach css module 3la wed page we7da, ndiro liha style inline wla global
// Hada l-wrapper li kay-centrer l-formulaire f l-wset dyal l-ecran
const pageStyle: React.CSSProperties = {
    minHeight: '100vh',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    background: 'radial-gradient(circle at top, var(--accent-blue) 0%, var(--bg-dark) 40%)'
};

export default function AuthPage() {
    return (
        <div style={pageStyle}>
            <FadeIn>
                <AuthForm />
            </FadeIn>
        </div>
    );
}