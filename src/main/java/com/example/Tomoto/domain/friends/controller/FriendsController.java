package com.example.Tomoto.domain.friends.controller;

import com.example.Tomoto.domain.friends.service.FriendService;
import com.example.Tomoto.global.annotation.Jwt;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/friends")
@Slf4j
@RequiredArgsConstructor
public class FriendsController {
    private final FriendService friendService;

    @PostMapping("")
    public String addFriend(@Parameter(hidden = true) @Jwt Long userId, String friendName){
        friendService.addFriend(userId, friendName);
        return "성공";
    }

    @PostMapping("/{friendId}")
    public String deleteFriend(@Parameter(hidden = true) @Jwt Long userId,
                               @PathVariable Long friendId){
        friendService.removeFriend(userId, friendId);
        return "성공";
    }
}
