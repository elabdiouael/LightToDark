package com.darktolight.api.services;

import com.darktolight.api.models.User;
import com.darktolight.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class GamificationService {

    private final UserRepository userRepository;

    @Transactional
    public User awardPointsAndCheckStreak(User user, int ayahsReadCount) {
        LocalDateTime now = LocalDateTime.now();

        // 1. Calcul dyal Streak (Istimrariya)
        if (user.getLastReadDate() != null) {
            LocalDate lastRead = user.getLastReadDate().toLocalDate();
            LocalDate today = now.toLocalDate();

            if (lastRead.isEqual(today.minusDays(1))) {
                user.setCurrentStreak(user.getCurrentStreak() + 1); // Zado nhar
            } else if (lastRead.isBefore(today.minusDays(1))) {
                user.setCurrentStreak(1); // T9t3at l-streak, kay3awd mn 1
            }
            // Ila kan today == today, makat-tbdelch l-streak
        } else {
            user.setCurrentStreak(1); // Awel nhar lihe
        }
        user.setLastReadDate(now);

        // 2. Calcul dyal Points Nour
        int basePoints = ayahsReadCount * 5; // 5 points l kol ayah

        // Bonus we9t Fajr awla Qiyam Lail (Ex: bin 3:00 d sbah w 6:00)
        LocalTime timeNow = now.toLocalTime();
        if (timeNow.isAfter(LocalTime.of(3, 0)) && timeNow.isBefore(LocalTime.of(6, 0))) {
            basePoints *= 2; // Double Points (We9t sda3 w khoucho3)
        }

        user.setNourPoints(user.getNourPoints() + basePoints);

        return userRepository.save(user);
    }
}