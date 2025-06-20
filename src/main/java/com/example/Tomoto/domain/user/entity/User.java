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
    @Builder.Default
    private String bio = "유저 한줄 소개를 입력해주세요.";

    @OneToMany(mappedBy = "user")
    private List<Todo> todos = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Music> musics = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Pomo> pomos = new ArrayList<>();

//    @ElementCollection(fetch = FetchType.LAZY)
//    @Builder.Default
//    private List<Boolean> challenges = IntStream.range(0, 11)
//            .mapToObj(i -> false)
//            .toList();
    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private List<Boolean> challenges = new ArrayList<>(
            IntStream.range(0, 11)
                    .mapToObj(i -> false)
                    .collect(Collectors.toList())
    );



    public static User create(String id, String password, String nickname) {
        return User.builder()
                .id(id)
                .password(password)
                .nickname(nickname)
                .createdAt(LocalDateTime.now())
                .build();
    }

    // 더미 친구 유저들 생성 메서드
    public static List<User> createDummyFriends() {
        List<String> friendNames = List.of("여운", "상희", "승연", "서윤", "시은");
        List<User> dummyFriends = new ArrayList<>();

        for (String name : friendNames) {
            User friend = User.builder()
                    .id("dummy_" + name.toLowerCase())
                    .password("dummy123") // 더미 비밀번호
                    .nickname(name)
                    .createdAt(LocalDateTime.now().minusDays((long)(Math.random() * 30))) // 랜덤한 가입일
                    .level((int)(Math.random() * 10) + 1) // 1-10 레벨
                    .xp((int)(Math.random() * 1000)) // 0-999 경험치
                    .totalPomo((int)(Math.random() * 50) + 10) // 10-59 뽀모도로
                    .bio(name + "의 한줄소개입니다!")
                    .build();
            dummyFriends.add(friend);
        }
        return dummyFriends;
    }

    public void updateXpAndLevel(int level, int xp) {
        this.level = level;
        this.xp = xp;
    }

    public void addMusic(Music music) {
        this.musics.add(music);
        this.setUserId(this.getUserId());
    }

    public void updateChallenges(List<Boolean> updatedChallenges) {
        this.challenges.clear();
        this.challenges.addAll(updatedChallenges);
    }

}
