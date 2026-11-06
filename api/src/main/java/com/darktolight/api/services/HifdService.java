package com.darktolight.api.services;

import com.darktolight.api.models.HifdRecord;
import com.darktolight.api.models.User;
import com.darktolight.api.repositories.UserRepository;
// import com.darktolight.api.repositories.HifdRecordRepository; // 3reftk at-gadha
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class HifdService {

    // private final HifdRecordRepository hifdRepository;
    private final UserRepository userRepository;

    @Transactional
    public HifdRecord reviewAyah(Long userId, int surah, int ayah, int quality) {
        // quality: 0 (Blackout) -> 5 (Perfect recall)
        User user = userRepository.findById(userId).orElseThrow();

        // Fetch wla creyi wehda jdida
        // HifdRecord record = hifdRepository.findByUserIdAndSurahNumberAndAyahNumber(userId, surah, ayah)
        //        .orElse(HifdRecord.builder().user(user).surahNumber(surah).ayahNumber(ayah).build());
        HifdRecord record = new HifdRecord(); // Placeholder, sta3ml ligne li lfo9

        if (quality < 3) {
            record.setRepetitions(0);
            record.setIntervalDays(1); // Nsaha, ghadi ychoufha ghedda
        } else {
            record.setRepetitions(record.getRepetitions() + 1);

            if (record.getRepetitions() == 1) {
                record.setIntervalDays(1);
            } else if (record.getRepetitions() == 2) {
                record.setIntervalDays(6);
            } else {
                record.setIntervalDays(Math.round(record.getIntervalDays() * record.getEaseFactor()));
            }
        }

        // Update Ease Factor (Kay-t7kem f s3oubiya l-merra jaya)
        float newEase = record.getEaseFactor() + (0.1f - (5 - quality) * (0.08f + (5 - quality) * 0.02f));
        record.setEaseFactor(Math.max(1.3f, newEase)); // Minimum ease hwa 1.3

        // Set Next Review Date
        record.setNextReviewDate(LocalDate.now().plusDays(record.getIntervalDays()));

        // return hifdRepository.save(record);
        return record;
    }
}