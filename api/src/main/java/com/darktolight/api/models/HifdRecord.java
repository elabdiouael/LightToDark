package com.darktolight.api.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "hifd_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HifdRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Integer surahNumber;

    @Column(nullable = false)
    private Integer ayahNumber;

    // SM-2 Algorithm Variables
    @Builder.Default
    private Integer repetitions = 0; // Chhal mn merra t-raj3at

    @Builder.Default
    private Float easeFactor = 2.5f; // S3oubiya dyal l-aya (kat-bda b 2.5)

    @Builder.Default
    private Integer intervalDays = 0; // Chhal mn nhar bach y3awd ychoufha

    @Column(nullable = false)
    private LocalDate nextReviewDate; // Mta ghadi t-tl3 lih f l-app bach y-raj3ha
}