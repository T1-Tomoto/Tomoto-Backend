package com.example.Tomoto.domain.pomo.repository;

import com.example.Tomoto.domain.pomo.entiry.Pomo;
import com.example.Tomoto.domain.user.entiry.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PomoRepository extends JpaRepository<Pomo, Long> {

    Optional<Pomo> findByUserAndCreatedAtBetween(User user, LocalDateTime start, LocalDateTime end);
}
