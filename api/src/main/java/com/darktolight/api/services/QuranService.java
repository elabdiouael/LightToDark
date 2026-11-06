package com.darktolight.api.services;

import com.darktolight.api.dto.QuranResponseDto;
import com.darktolight.api.dto.SurahMetaDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuranService {

    private final RestClient restClient;
    private static final String ALQURAN_CLOUD_BASE_URL = "https://api.alquran.cloud/v1";

    /**
     * Fetch ga3 les sourates (Metadata)
     * Caching key: 'surahList'
     */
    @Cacheable(value = "quranCache", key = "'surahList'")
    public List<SurahMetaDto> getAllSurahs() {
        log.info("Fetching all surahs from Al Quran Cloud API (Not from Cache)");

        QuranResponseDto<List<SurahMetaDto>> response = restClient.get()
                .uri(ALQURAN_CLOUD_BASE_URL + "/surah")
                .retrieve()
                .body(new ParameterizedTypeReference<QuranResponseDto<List<SurahMetaDto>>>() {});

        if (response != null && response.getCode() == 200) {
            return response.getData();
        }
        throw new RuntimeException("Failed to fetch Surahs");
    }

    /**
     * Fetch sourate kamla b text 3arabi + Audio dyal mou9ri2
     * Caching key: 'surah_id_audio_edition'
     */
    @Cacheable(value = "quranCache", key = "'surah_' + #surahNumber + '_audio_' + #audioEdition")
    public Object getSurahWithAudio(int surahNumber, String audioEdition) {
        log.info("Fetching surah {} with audio {} from API", surahNumber, audioEdition);

        // Exemple: /surah/1/ar.alafasy
        String endpoint = String.format("%s/surah/%d/%s", ALQURAN_CLOUD_BASE_URL, surahNumber, audioEdition);

        return restClient.get()
                .uri(endpoint)
                .retrieve()
                .body(Object.class); // Kn-rj3o Object direct bach y-siftou l-controller (Json raw)
    }

    /**
     * Fetch Ayah Random l-Dashboard (Aya dyal lyoum)
     */
    public Object getRandomAyah() {
        log.info("Fetching random Ayah");
        return restClient.get()
                .uri(ALQURAN_CLOUD_BASE_URL + "/ayah/random/ar.alafasy")
                .retrieve()
                .body(Object.class);
    }
}