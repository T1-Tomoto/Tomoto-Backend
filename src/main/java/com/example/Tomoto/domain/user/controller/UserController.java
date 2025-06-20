package com.example.Tomoto.domain.user.controller;

import com.example.Tomoto.domain.user.dto.request.ChallengeUpdateReq;
import com.example.Tomoto.domain.user.dto.request.LevelAndExpUpdateReq;
import com.example.Tomoto.domain.user.dto.request.UserLoginReq;
import com.example.Tomoto.domain.user.dto.request.UserRegisterReq;
import com.example.Tomoto.domain.user.dto.response.UserInfoRes;
import com.example.Tomoto.domain.user.dto.response.UserTokenRes;
import com.example.Tomoto.domain.user.service.UserService;
import com.example.Tomoto.global.annotation.Jwt;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "유저", description = "유저 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Operation(summary = "회원가입", description = "유저가 회원가입합니다. 더미 친구, Todo, Pomo 히스토리가 자동으로 생성됩니다.")
    @PostMapping("/signup")
    public ResponseEntity<UserTokenRes> register(
            @Validated @RequestBody UserRegisterReq req
    ) {
        return ResponseEntity.ok(userService.register(req));
    }

    @Operation(summary = "로그인", description = "유저가 로그인합니다.")
    @PostMapping("/login")
    public ResponseEntity<UserTokenRes> login(
            @Validated @RequestBody UserLoginReq req
    ){
        return ResponseEntity.ok(userService.login(req));
    }

    @Operation(summary = "설정", description = "로그인한 유저의 정보를 보여줍니다.")
    @GetMapping("/info")
    public ResponseEntity<UserInfoRes> getUserSettings(@Parameter(hidden = true) @Jwt Long userId) {
        return ResponseEntity.ok(userService.settings(userId));
    }

    @Operation(summary = "한줄 소개 업데이트", description = "유저 한 줄 소개란을 업데이트 합니다.")
    @PatchMapping("/info/bio")
    public void updateUserBio(@Parameter(hidden = true) @Jwt Long userId, @RequestParam String newBio) {
        userService.updateUserBio(userId, newBio);
    }

    @Operation(summary = "레벨 업데이트", description = "레벨 및 xp 정보 업데이트")
    @PatchMapping("/level")
    public void levelXpUp(@Parameter(hidden = true) @Jwt Long userId, @RequestBody LevelAndExpUpdateReq req) {
        userService.levelXpUp(userId, req);
    }

    @PatchMapping("/challenges")
    @Operation(summary = "챌린지 상태 수정", description = "13개 챌린지 상태를 수정합니다.")
    public ResponseEntity<Void> updateChallenges(
            @Parameter(hidden = true) @Jwt Long userId,
            @RequestBody List<Boolean> req
    ) {
        userService.updateChallenges(userId, req);
        return ResponseEntity.ok().build();
    }
}