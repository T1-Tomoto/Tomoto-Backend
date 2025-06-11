package com.example.Tomoto.domain.todo.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record TodoMainRes(
        LocalDateTime createdAt,
        LocalDateTime dueDate,
        String content,
        boolean completed) {

//    @Schema(description = "투두를 만든 날짜")
//    @NotBlank
//    private LocalDateTime createdAt;
//
//    @Schema(description = "투두의 기한")
//    private LocalDateTime dueDate;
//
//    @Schema(description = "내용")
//    @NotBlank
//    private String content;
//
//    @Schema(description = "완료 여부")
//    @NotBlank
//    private boolean completed;
}
