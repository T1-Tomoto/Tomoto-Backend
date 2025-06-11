package com.example.Tomoto.domain.todo.service;

import com.example.Tomoto.domain.todo.dto.response.TodoMainRes;
import com.example.Tomoto.domain.todo.entity.Todo;
import com.example.Tomoto.domain.todo.repository.TodoRepository;
import com.example.Tomoto.domain.user.entity.User;
import com.example.Tomoto.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public List<TodoMainRes> findAll(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return todoRepository.findAllByUserOrderByCreatedAt(user);
    }
}
