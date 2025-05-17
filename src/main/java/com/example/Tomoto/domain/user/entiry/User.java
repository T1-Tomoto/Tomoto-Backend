package com.example.Tomoto.domain.user.entiry;

import com.example.Tomoto.domain.friends.entiry.Friend;
import com.example.Tomoto.domain.music.entiry.Music;
import com.example.Tomoto.domain.pomo.entiry.Pomo;
import com.example.Tomoto.domain.todo.entiry.Todo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String login_id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    private int level;
    private int total_pomo;

    @OneToMany(mappedBy = "user")
    private List<Todo> todos = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Friend> friends = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Music> musics = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Pomo> pomos = new ArrayList<>();
}
