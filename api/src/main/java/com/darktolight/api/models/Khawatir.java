package com.darktolight.api.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "khawatir")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Khawatir {

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

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content; // Dakchi li kteb l-user (Reflection dyalo)

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}