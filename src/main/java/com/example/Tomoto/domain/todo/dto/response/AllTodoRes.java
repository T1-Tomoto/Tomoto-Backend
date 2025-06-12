package com.example.Tomoto.domain.todo.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record AllTodoRes(

        @Schema(description = "투두의 기한")
        LocalDateTime dueDate,

        @Schema(description = "내용")
        @NotBlank
        String content,

        @Schema(description = "완료 여부")
        @NotBlank
        boolean completed) {

}
