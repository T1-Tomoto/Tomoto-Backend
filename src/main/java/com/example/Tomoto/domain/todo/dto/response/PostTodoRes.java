package com.example.Tomoto.domain.todo.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record PostTodoRes(
        @Schema(description = "생성된 투두의 ID")
        Long todoId
) {
}
