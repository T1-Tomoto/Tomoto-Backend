package com.example.Tomoto.domain.user.entity;

import com.example.Tomoto.domain.friends.entity.Friend;
import com.example.Tomoto.domain.music.entity.Music;
import com.example.Tomoto.domain.pomo.entity.Pomo;
import com.example.Tomoto.domain.todo.entity.Todo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Entity
@Getter
@Builder
@Setter
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
    private String id; //로그인 id값

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Builder.Default
    @Setter
    private int level = 1;

    @Setter
    private int xp = 0;

    @Setter
    private int totalPomo = 1; //지금까지 공부한 뽀모도로 수

    @Column(length = 100)
    private String bio = "유저 한줄 소개를 입력해주세요.";

    @OneToMany(mappedBy = "user")
    private List<Todo> todos = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Music> musics = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Pomo> pomos = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private List<Boolean> challenges = IntStream.range(0, 13)
            .mapToObj(i -> false)
            .toList();


    public static User create(String id, String password, String nickname) {
        return User.builder()
                .id(id)
                .password(password)
                .nickname(nickname)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public void updateXpAndLevel(int level, int xp) {
        this.level = level;
        this.xp = xp;
    }

    public void addMusic(Music music) {
        this.musics.add(music);
        this.setUserId(this.getUserId());
    }
}
