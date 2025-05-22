package com.example.Tomoto.domain.user.entiry;

import com.example.Tomoto.domain.friends.entiry.Friend;
import com.example.Tomoto.domain.music.entiry.Music;
import com.example.Tomoto.domain.pomo.entiry.Pomo;
import com.example.Tomoto.domain.todo.entiry.Todo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(builder = User.UserBuilder.class)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false, length = 500)
    private LocalDateTime createdAt;

    private int level;
    private int totalPomo;

    @OneToMany(mappedBy = "user")
    private List<Todo> todos = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Friend> friends = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Music> musics = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Pomo> pomos = new ArrayList<>();


    public static User create(String id, String password, String nickname) {
        return User.builder()
                .id(id)
                .password(password)
                .nickname(nickname)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
