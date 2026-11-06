package com.darktolight.api.repositories;

import com.darktolight.api.models.DuoKhatmaRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DuoKhatmaRoomRepository extends JpaRepository<DuoKhatmaRoom, Long> {
    Optional<DuoKhatmaRoom> findByRoomCode(String roomCode);
}