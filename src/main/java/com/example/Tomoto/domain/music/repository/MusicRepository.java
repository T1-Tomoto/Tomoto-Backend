package com.example.Tomoto.domain.music.repository;

import com.example.Tomoto.domain.music.entity.Music;
import com.example.Tomoto.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicRepository extends JpaRepository<Music, Long> {
    List<Music> findAllByUser(User user);
}
