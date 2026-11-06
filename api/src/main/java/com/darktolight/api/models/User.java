package com.darktolight.api.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    private String password; // Mn b3d ghan-cryptiwha b Spring Security

    // Gamification (L-herba li hderna 3liha)
    @Builder.Default
    private Integer nourPoints = 0; // Chhal mn point jme3

    @Builder.Default
    private Integer currentStreak = 0; // Chhal mn nhar mtabe3 l-9raya

    private LocalDateTime lastReadDate; // Bach n-checkiw l-streak wach t9te3 wla la

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}