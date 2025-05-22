package com.example.Tomoto.domain.user.controller;

import com.example.Tomoto.domain.user.dto.request.UserLoginReq;
import com.example.Tomoto.domain.user.dto.request.UserRegisterReq;
import com.example.Tomoto.domain.user.dto.response.UserTokenRes;
import com.example.Tomoto.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "유저", description = "유저 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Operation(summary = "회원가입", description = "유저가 회원가입합니다.")
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
}
