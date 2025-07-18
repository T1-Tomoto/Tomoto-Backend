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

//    @CreationTimestamp
    @Setter
    private LocalDateTime createdAt;

    @Setter
    @Builder.Default
    private int pomoNum = 1; //오늘 하루동안 몇 번

    @PrePersist
    private void setDefaults() {
        if (this.pomoNum == 0) {
            this.pomoNum = 1;
        }
    }

    public static Pomo create(User user){
        return Pomo.builder()
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
