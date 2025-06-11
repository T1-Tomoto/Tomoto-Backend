package com.example.Tomoto.domain.todo.controller;

import com.example.Tomoto.domain.pomo.dto.response.DailyPomoCountDto;
import com.example.Tomoto.domain.pomo.service.PomoService;
import com.example.Tomoto.domain.todo.dto.request.AddTodoReq;
import com.example.Tomoto.domain.todo.dto.response.TodoMainRes;
import com.example.Tomoto.domain.todo.dto.response.TodoPageRes;
import com.example.Tomoto.domain.todo.service.TodoService;
import com.example.Tomoto.global.annotation.Jwt;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "투두 리스트", description = "투두리스트 API")
@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;
    private final PomoService pomoService;

    @GetMapping("")
    @Operation(summary = "투두 페이지", description = "투두리스트 페이지에 필요한 모든 정보를 반환합니다: 날짜별 뽀모도로 횟수, 날짜별 투두리스트 내용 등")
    public TodoPageRes getAllTodo(@Parameter(hidden = true) @Jwt Long userId){
        List<DailyPomoCountDto> dailyPomoCountDtos = pomoService.dailyPomoCount(userId);
        List<TodoMainRes> todoMainResList = todoService.findAll(userId);
        return new TodoPageRes(dailyPomoCountDtos, todoMainResList);
    }

    @PostMapping("/add")
    @Operation(summary = "투두 추가", description = "유저가 입력한 투두를 추가합니다.")
    public ResponseEntity<AddTodoReq> addTodo(@Parameter(hidden = true) @Jwt Long userId, @RequestBody AddTodoReq req){
        return todoService.addTodo(userId, req);
    }
}
