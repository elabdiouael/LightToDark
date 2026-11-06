package com.darktolight.api.controllers;

import com.darktolight.api.models.Khawatir;
import com.darktolight.api.models.User;
import com.darktolight.api.repositories.KhawatirRepository;
import com.darktolight.api.repositories.UserRepository;
import com.darktolight.api.services.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/progress")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProgressController {

    private final ProgressService progressService;
    private final KhawatirRepository khawatirRepository;
    private final UserRepository userRepository;

    // --- Personal Khatma ---
    @PostMapping("/khatma/{khatmaId}/update")
    public ResponseEntity<String> updateProgress(
            @PathVariable Long khatmaId,
            @RequestParam Long userId,
            @RequestParam int surah,
            @RequestParam int ayah,
            @RequestParam int ayahsRead) {

        progressService.updatePersonalKhatma(khatmaId, userId, surah, ayah, ayahsRead);
        return ResponseEntity.ok("Progress updated w points tzado!");
    }

    // --- Duo Khatma (Co-op) ---
    @PostMapping("/duo/create")
    public ResponseEntity<String> createDuoRoom(@RequestParam Long creatorId) {
        return ResponseEntity.ok(progressService.createDuoRoom(creatorId));
    }

    @PostMapping("/duo/join")
    public ResponseEntity<String> joinDuoRoom(@RequestParam Long partnerId, @RequestParam String roomCode) {
        progressService.joinDuoRoom(partnerId, roomCode);
        return ResponseEntity.ok("Dkhelti l-room b naja7!");
    }

    @PostMapping("/duo/{roomCode}/update")
    public ResponseEntity<String> updateDuoProgress(
            @PathVariable String roomCode,
            @RequestParam Long userId,
            @RequestParam int surah,
            @RequestParam int ayah,
            @RequestParam int ayahsRead) {

        progressService.updateDuoKhatmaProgress(roomCode, userId, surah, ayah, ayahsRead);
        return ResponseEntity.ok("Duo Khatma updated!");
    }

    // --- Journaling (Khawatir) ---
    @PostMapping("/khawatir/add")
    public ResponseEntity<Khawatir> addKhawatir(
            @RequestParam Long userId,
            @RequestParam int surah,
            @RequestParam int ayah,
            @RequestBody String content) {

        User user = userRepository.findById(userId).orElseThrow();
        Khawatir khawatir = Khawatir.builder()
                .user(user)
                .surahNumber(surah)
                .ayahNumber(ayah)
                .content(content)
                .build();

        return ResponseEntity.ok(khawatirRepository.save(khawatir));
    }

    @GetMapping("/khawatir/{userId}")
    public ResponseEntity<List<Khawatir>> getUserKhawatir(@PathVariable Long userId) {
        return ResponseEntity.ok(khawatirRepository.findByUserIdOrderByCreatedAtDesc(userId));
    }
}