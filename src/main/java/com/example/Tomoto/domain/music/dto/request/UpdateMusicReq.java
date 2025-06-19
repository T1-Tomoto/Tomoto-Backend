package com.example.Tomoto.domain.music.dto.request;

public record UpdateMusicReq(
        String oldUrl,
        String newUrl
) {
}
