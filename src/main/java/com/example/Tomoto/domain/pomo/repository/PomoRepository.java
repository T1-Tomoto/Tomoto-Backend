package com.example.Tomoto.domain.pomo.repository;

import com.example.Tomoto.domain.pomo.dto.response.DailyPomoCountDto;
import com.example.Tomoto.domain.pomo.entity.Pomo;
import com.example.Tomoto.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PomoRepository extends JpaRepository<Pomo, Long> {

    Optional<Pomo> findByUserAndCreatedAtBetween(User user, LocalDateTime start, LocalDateTime end);

    List<DailyPomoCountDto> findByUserOrderByCreatedAtDesc(User user);
    List<Pomo> findByUser(User user);
}
