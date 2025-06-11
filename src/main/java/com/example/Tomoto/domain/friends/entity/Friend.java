package com.example.Tomoto.domain.friends.entity;

import com.example.Tomoto.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_id")
    private Long id;

    private Long fromId; //추가 요청을 보낸 유저 아이디
    private Long toId; //요청을 받은 친구 아이디

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

    public static Friend create(Long fromId, Long toId) {
        return Friend.builder()
                .fromId(fromId)
                .toId(toId)
                .build();
    }
}
