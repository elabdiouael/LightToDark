package com.darktolight.api.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "duo_khatma_rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DuoKhatmaRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String roomCode; // Code bhal "X7Y9A" bach y-inviti sa7bo

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partner_id")
    private User partner; // Y9der ykoun null f l-bdya hta y-joini sa7bo

    @Builder.Default
    private Integer currentSurah = 1;

    @Builder.Default
    private Integer currentAyah = 1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_read_by")
    private User lastReadBy; // Chkoun li 9ra akher merra bach n3erfo twba dyal mn

    @Builder.Default
    private Boolean isCompleted = false;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime lastReadAt;
}