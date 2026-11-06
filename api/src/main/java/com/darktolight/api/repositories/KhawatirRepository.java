package com.darktolight.api.repositories;

import com.darktolight.api.models.Khawatir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KhawatirRepository extends JpaRepository<Khawatir, Long> {
    List<Khawatir> findByUserIdOrderByCreatedAtDesc(Long userId);
    List<Khawatir> findByUserIdAndSurahNumberAndAyahNumber(Long userId, Integer surahNumber, Integer ayahNumber);
}