import React from 'react';
import styles from './NourPointsCard.module.css';

interface NourPointsCardProps {
    points: number;
}

export const NourPointsCard = ({ points }: NourPointsCardProps) => {
    return (
        // dkhlna .glass-panel mn globals w l-container mn css module
        <div className={`glass-panel ${styles.cardContainer}`}>
            <span className={styles.title}>Nour Points</span>
            <span className={styles.points}>{points}</span>
        </div>
    );
};