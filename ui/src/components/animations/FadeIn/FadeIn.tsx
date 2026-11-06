import React, { ReactNode } from 'react';
import styles from './FadeIn.module.css';

interface FadeInProps {
    children: ReactNode;
}

export const FadeIn = ({ children }: FadeInProps) => {
    return (
        <div className={styles.fadeInWrapper}>
            {children}
        </div>
    );
};