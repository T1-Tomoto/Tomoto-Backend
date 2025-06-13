package com.example.Tomoto.domain.music.service;

import com.example.Tomoto.domain.music.dto.response.MusicListRes;
import com.example.Tomoto.domain.music.entity.Music;
import com.example.Tomoto.domain.music.repository.MusicRepository;
import com.example.Tomoto.domain.user.entity.User;
import com.example.Tomoto.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MusicService {
    private final MusicRepository musicRepository;
    private final UserRepository userRepository;

    public List<MusicListRes> getAllMusic(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        List<Music> musics = musicRepository.findAllByUser(user);

        return musics.stream()
                .map(music -> new MusicListRes(music.getId(), music.getUrl()))
                .toList();
    }

    public void addMusic(Long userId, String url) {
        User user = userRepository.findById(userId).orElseThrow();
        Music music = Music.create(user, url);
        musicRepository.save(music);
    }
}
