package com.example.Tomoto.domain.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoRes {

    @Schema(description = "아이디")
    @NotBlank
    private String id;

    @Schema(description = "닉네임")
    @NotBlank
    private String nickname;

    @Schema(description = "레벨")
    @NotBlank
    private int level;

    @Schema(description = "유저의 총 포모도로 횟수")
    @NotBlank
    private int totalPomo;
}
