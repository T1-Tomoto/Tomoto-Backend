package com.example.Tomoto.domain.user.dto.response;

public record AllUserInfoRes(
        Long userId,
        String nickname,
        int level,
        int xp
) {
}
