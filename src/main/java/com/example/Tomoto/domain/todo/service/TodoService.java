package com.example.Tomoto.domain.todo.service;

import com.example.Tomoto.domain.todo.dto.request.AddTodoReq;
import com.example.Tomoto.domain.todo.dto.response.TodoMainRes;
import com.example.Tomoto.domain.todo.entity.Todo;
import com.example.Tomoto.domain.todo.repository.TodoRepository;
import com.example.Tomoto.domain.user.entity.User;
import com.example.Tomoto.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<AddTodoReq> addTodo(Long usserId, AddTodoReq req) {
        User user = userRepository.findById(usserId).orElseThrow();
        Todo newTodo = Todo.create(
                user, req.getContent(), req.getDueDtae()
        );
        todoRepository.save(newTodo);
        return ResponseEntity.ok(req);
    }
}
