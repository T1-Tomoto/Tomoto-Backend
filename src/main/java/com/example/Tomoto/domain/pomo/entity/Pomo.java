package com.example.Tomoto.domain.pomo.entity;

import com.example.Tomoto.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pomo {

    //하루에 한 개 생성: 하루의 정보만 들어감
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pomo_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Setter
    private int pomo_num = 1; //오늘 하루동안 몇 번

    public static Pomo create(User user){
        return Pomo.builder()
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
