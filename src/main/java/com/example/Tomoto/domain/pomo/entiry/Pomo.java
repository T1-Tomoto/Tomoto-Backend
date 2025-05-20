package com.example.Tomoto.domain.pomo.entiry;

import com.example.Tomoto.domain.user.entiry.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
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
    private LocalDateTime created_at;

    private int pomo_num; //오늘 하루동안 몇 번
}
