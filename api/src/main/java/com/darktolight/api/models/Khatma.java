package com.darktolight.api.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "khatmas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Khatma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Kol khatma dial wahed l-user (Relation ManyToOne)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    @Builder.Default
    private Integer currentSurah = 1; // Katbda dima mn Al-Fatiha

    @Column(nullable = false)
    @Builder.Default
    private Integer currentAyah = 1;

    @Builder.Default
    private Boolean isCompleted = false; // Mli ykhetem l-quran katwli true

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime startedAt;

    @UpdateTimestamp
    private LocalDateTime lastReadAt; // Akhir merra 9ra f had l-khatma
}