package com.example.Tomoto.domain.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterReq {

    @Schema(description = "아이디")
    @NotBlank
    private String id;

    @Schema(description = "비밀번호")
    @NotBlank
    private String password;

    @Schema(description = "닉네임")
    @NotBlank
    private String nickname;
}
