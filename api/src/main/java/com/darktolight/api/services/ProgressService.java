package com.darktolight.api.services;

import com.darktolight.api.models.DuoKhatmaRoom;
import com.darktolight.api.models.Khatma;
import com.darktolight.api.models.User;
import com.darktolight.api.repositories.DuoKhatmaRoomRepository;
import com.darktolight.api.repositories.KhatmaRepository;
import com.darktolight.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProgressService {

    private final KhatmaRepository khatmaRepository;
    private final DuoKhatmaRoomRepository duoRoomRepository;
    private final UserRepository userRepository;
    private final GamificationService gamificationService;

    @Transactional
    public void updatePersonalKhatma(Long khatmaId, Long userId, int surah, int ayah, int ayahsReadCount) {
        Khatma khatma = khatmaRepository.findByIdAndUserId(khatmaId, userId)
                .orElseThrow(() -> new RuntimeException("Khatma not found"));

        khatma.setCurrentSurah(surah);
        khatma.setCurrentAyah(ayah);
        khatmaRepository.save(khatma);

        User user = userRepository.findById(userId).orElseThrow();
        gamificationService.awardPointsAndCheckStreak(user, ayahsReadCount);
    }

    @Transactional
    public String createDuoRoom(Long creatorId) {
        User creator = userRepository.findById(creatorId).orElseThrow();
        String roomCode = UUID.randomUUID().toString().substring(0, 6).toUpperCase(); // Ex: A8F9B2

        DuoKhatmaRoom room = DuoKhatmaRoom.builder()
                .roomCode(roomCode)
                .creator(creator)
                .build();

        duoRoomRepository.save(room);
        return roomCode;
    }

    @Transactional
    public void joinDuoRoom(Long partnerId, String roomCode) {
        User partner = userRepository.findById(partnerId).orElseThrow();
        DuoKhatmaRoom room = duoRoomRepository.findByRoomCode(roomCode)
                .orElseThrow(() -> new RuntimeException("Room ma kaynaach"));

        if (room.getPartner() != null) {
            throw new RuntimeException("Room déjà 3amra b 2 nass");
        }

        room.setPartner(partner);
        duoRoomRepository.save(room);
    }

    @Transactional
    public void updateDuoKhatmaProgress(String roomCode, Long userId, int surah, int ayah, int ayahsReadCount) {
        DuoKhatmaRoom room = duoRoomRepository.findByRoomCode(roomCode).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();

        room.setCurrentSurah(surah);
        room.setCurrentAyah(ayah);
        room.setLastReadBy(user);
        duoRoomRepository.save(room);

        gamificationService.awardPointsAndCheckStreak(user, ayahsReadCount);
    }
}