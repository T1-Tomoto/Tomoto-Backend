package com.example.Tomoto.domain.music.entity;

import com.example.Tomoto.domain.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "music_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String url;

    public static Music create(User user, String url) {
        Music music = new Music();
        music.setUser(user);   // 연관관계 주입
        music.setUrl(url);
        user.addMusic(music);  // 양방향 설정
        return music;
    }
}
