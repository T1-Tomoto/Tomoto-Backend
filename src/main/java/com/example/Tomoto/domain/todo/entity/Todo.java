package com.example.Tomoto.domain.todo.entity;

import com.example.Tomoto.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long todoId;

//    @CreationTimestamp
//    private LocalDateTime createdAt;

    private LocalDateTime dueDate;

    private String content;

    private boolean completed; //해당 투두를 했는지 안 했는지

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public static Todo create(User user, String content, LocalDateTime dueDate) {
        return Todo.builder()
                .user(user)
                .content(content)
                .dueDate(dueDate)
                .build();
    }
}
