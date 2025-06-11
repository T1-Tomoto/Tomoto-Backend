package com.example.Tomoto.domain.todo.repository;

import com.example.Tomoto.domain.todo.dto.response.TodoMainRes;
import com.example.Tomoto.domain.todo.entity.Todo;
import com.example.Tomoto.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query("SELECT new com.example.Tomoto.domain.todo.dto.response.TodoMainRes(t.createdAt, t.dueDate, t.content, t.completed) " +
            "FROM Todo t WHERE t.user = :user ORDER BY t.createdAt ASC")
    List<TodoMainRes> findAllByUserOrderByCreatedAt(@Param("user") User user);
}
