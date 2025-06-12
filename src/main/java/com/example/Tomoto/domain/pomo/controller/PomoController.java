package com.example.Tomoto.domain.pomo.controller;

import com.example.Tomoto.domain.pomo.dto.response.DailyPomoCountDto;
import com.example.Tomoto.domain.pomo.repository.PomoRepository;
import com.example.Tomoto.domain.pomo.service.PomoService;
import com.example.Tomoto.domain.todo.dto.response.AllTodoRes;
import com.example.Tomoto.global.annotation.Jwt;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "뽀모도로", description = "뽀모도로, 타이머 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/pomos")
public class PomoController {
    private final PomoService pomoService;

    @GetMapping("")
    @Operation(summary = "뽀모도로 횟수 가져오기", description = "유저가 오늘 공부한 뽀모도로 횟수를 가져옵니다. 타이머 화면에서 사용합니다.")
    public int getTodayPomo(@Parameter(hidden = true) @Jwt Long userId) {
        return pomoService.getTodayPomo(userId);
    }

    @PostMapping("/add")
    @Operation(summary = "뽀모도로 횟수 추가하기", description = "25분 타이머가 끝나고 뽀모도로 횟수를 +1 합니다.")
    public void addPomo(@Parameter(hidden = true) @Jwt Long userId) {
        pomoService.addTodayPomo(userId);
    }

    @GetMapping("/history")
    @Operation(summary = "모든 뽀모도로 횟수 가져오기", description = "뽀모도로 기록을 가져옵니다. 투두리스트 캘린더에서 사용합니다.")
    public List<DailyPomoCountDto> getAllTodo(@Parameter(hidden = true) @Jwt Long userId){
        return pomoService.dailyPomoCount(userId);
    }

}
