package com.darktolight.api.repositories;

import com.darktolight.api.models.Khatma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KhatmaRepository extends JpaRepository<Khatma, Long> {
    List<Khatma> findByUserIdOrderByLastReadAtDesc(Long userId); // Bach njibou akhir khatma 9ra fiha
    Optional<Khatma> findByIdAndUserId(Long khatmaId, Long userId);
}