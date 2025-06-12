package com.example.Tomoto.domain.todo.service;

import com.example.Tomoto.domain.todo.dto.request.AddTodoReq;
import com.example.Tomoto.domain.todo.dto.response.AllTodoRes;
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

    public List<AllTodoRes> findAll(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return todoRepository.findAllByUserOrderByCreatedAt(user);
    }

    public Long addTodo(Long usserId, AddTodoReq req) {
        User user = userRepository.findById(usserId).orElseThrow();
        Todo newTodo = Todo.create(
                user, req.getContent(), req.getDueDate()
        );
        todoRepository.save(newTodo);
        return newTodo.getTodoId();
    }

    public void deleteTodo(Long todoId) {
        Todo deleteTodo = todoRepository.findById(todoId).orElseThrow();
        todoRepository.delete(deleteTodo);
    }
}
