package com.darktolight.api.controllers;

import com.darktolight.api.dto.SurahMetaDto;
import com.darktolight.api.services.QuranService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quran")
@CrossOrigin(origins = "*") // 3la 9bl Next.js bash ma-y3tiksh CORS error fl-Lokal
@RequiredArgsConstructor
public class QuranController {

    private final QuranService quranService;

    @GetMapping("/surahs")
    public ResponseEntity<List<SurahMetaDto>> getAllSurahs() {
        return ResponseEntity.ok(quranService.getAllSurahs());
    }

    @GetMapping("/surah/{surahNumber}/audio/{audioEdition}")
    public ResponseEntity<Object> getSurahWithAudio(
            @PathVariable int surahNumber,
            @PathVariable String audioEdition) {
        // Exemple audioEdition: "ar.alafasy"
        return ResponseEntity.ok(quranService.getSurahWithAudio(surahNumber, audioEdition));
    }

    @GetMapping("/ayah/random")
    public ResponseEntity<Object> getRandomAyah() {
        return ResponseEntity.ok(quranService.getRandomAyah());
    }
}