package com.example.Tomoto.domain.music.controller;

import com.example.Tomoto.domain.music.dto.response.MusicListRes;
import com.example.Tomoto.domain.music.entity.Music;
import com.example.Tomoto.domain.music.service.MusicService;
import com.example.Tomoto.domain.user.dto.request.UserRegisterReq;
import com.example.Tomoto.domain.user.dto.response.UserTokenRes;
import com.example.Tomoto.global.annotation.Jwt;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "음악", description = "음악 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/musics")
public class MusicController {
    private final MusicService musicService;

    @Operation(summary = "음악 리스트", description = "유저의 음악 리스트를 불러옵니다.")
    @GetMapping("")
    public ResponseEntity<List<MusicListRes>> register(@Parameter(hidden = true) @Jwt Long userId ) {
        return ResponseEntity.ok(musicService.getAllMusic(userId));
    }

    @Operation(summary = "음악 추가", description = "새로운 음악을 추가합니다.")
    @PostMapping("")
    public void addMusic(@Parameter(hidden = true) @Jwt Long userId, @RequestParam String url ) {
        musicService.addMusic(userId, url);
    }

    @Operation(summary = "음악 삭제", description = "선택한 음악을 삭제합니다.")
    @DeleteMapping("")
    public void deleteMusic(@Parameter(hidden = true) @Jwt Long userId, @RequestParam String url) {
        musicService.deleteMusic(userId, url);
    }

    @Operation(summary = "음악 수정", description = "선택한 음악의 Url을 수정합니다.")
    @PatchMapping("")
    public void updateMusic(@Parameter(hidden = true) @Jwt Long userId, @RequestParam String url) {
        musicService.updateMusic(userId, url);
    }
}
