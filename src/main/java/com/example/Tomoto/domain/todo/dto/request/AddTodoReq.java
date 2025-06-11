package com.example.Tomoto.domain.todo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AddTodoReq {
    @Schema(description = "마감기한")
    private LocalDateTime dueDtae;

    @Schema(description = "투두 내용")
    private String content;
}
