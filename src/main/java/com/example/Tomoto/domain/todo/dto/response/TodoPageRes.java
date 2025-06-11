package com.example.Tomoto.domain.todo.dto.response;

import com.example.Tomoto.domain.pomo.dto.response.DailyPomoCountDto;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record TodoPageRes(
        @Schema(description = "날짜별 뽀모도로 횟수 목록")
        List<DailyPomoCountDto> dailyPomoCounts,
        @Schema(description = "투두 목록")
        List<TodoMainRes> todos) {
}
