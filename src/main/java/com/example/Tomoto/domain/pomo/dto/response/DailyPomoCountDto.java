package com.example.Tomoto.domain.pomo.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class DailyPomoCountDto {

    @Schema(description = "날짜")
    @NotBlank
    private LocalDateTime createddAt;

    @Schema(description = "해당 날짜의 뽀모도로 횟수")
    @NotBlank
    private int pomoNum;
}
