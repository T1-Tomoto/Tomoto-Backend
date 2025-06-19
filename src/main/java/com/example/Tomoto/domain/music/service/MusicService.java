package com.example.Tomoto.domain.music.service;

import com.example.Tomoto.domain.music.dto.request.UpdateMusicReq;
import com.example.Tomoto.domain.music.dto.response.MusicListRes;
import com.example.Tomoto.domain.music.entity.Music;
import com.example.Tomoto.domain.music.repository.MusicRepository;
import com.example.Tomoto.domain.user.entity.User;
import com.example.Tomoto.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
//
//        Music music = musicRepository.findByUserAndUrl(user, url)
//                .orElseThrow(() -> new IllegalArgumentException("해당 음악이 존재하지 않습니다."));

        musicRepository.save(music);
    }

    public void deleteMusic(Long userId, String url) {
        User user = userRepository.findById(userId).orElseThrow();
        Music music = musicRepository.findByUserAndUrl(user, url)
                .orElseThrow(() -> new IllegalArgumentException("해당 음악이 존재하지 않습니다."));
        musicRepository.delete(music);
    }

    public void updateMusic(Long userId, UpdateMusicReq req) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다. ID: " + userId));

        Music music = musicRepository.findByUserAndUrl(user, req.oldUrl())
                .orElseThrow(() -> new IllegalArgumentException("해당 URL에 대한 음악이 존재하지 않습니다. URL: " + req.oldUrl()));
        music.setUrl(req.newUrl());
        musicRepository.save(music);
    }
}
