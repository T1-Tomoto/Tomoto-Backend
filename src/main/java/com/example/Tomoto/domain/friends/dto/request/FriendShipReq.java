package com.example.Tomoto.domain.friends.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FriendShipReq {
    @Schema(description = "친구 아이디")
    @NotBlank
    private String id;
}

