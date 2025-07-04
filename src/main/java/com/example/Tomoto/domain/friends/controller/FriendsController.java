package com.example.Tomoto.domain.friends.controller;

import com.example.Tomoto.domain.friends.dto.request.AddFriendReq;
import com.example.Tomoto.domain.friends.dto.request.FriendShipReq;
import com.example.Tomoto.domain.friends.dto.response.AddFriendRes;
import com.example.Tomoto.domain.friends.dto.response.FriendsRankRes;
import com.example.Tomoto.domain.friends.service.FriendService;
import com.example.Tomoto.domain.user.dto.response.AllUserInfoRes;
import com.example.Tomoto.domain.user.service.UserService;
import com.example.Tomoto.global.annotation.Jwt;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "랭킹 시스템", description = "전체 랭킹, 친구 랭킹, 친구 추가 관련 API")
@RestController
@RequestMapping("/ranking")
@Slf4j
@RequiredArgsConstructor
public class FriendsController {
    private final FriendService friendService;
    private final UserService userService;

    @GetMapping("")
    @Operation(summary = "랭킹 조회", description = "전체 유저의 랭킹을 조회합니다.")
    public List<AllUserInfoRes> getAllUserRanking(){
        return userService.getAllUserInfo();
    }

    @PostMapping("/friends")
    @Operation(summary = "친구 추가", description = "친구를 추가합니다.")
    public void addFriend(@Parameter(hidden = true) @Jwt Long userId, @RequestBody AddFriendReq req){
        friendService.addFriend(userId, req);
    }

    @GetMapping("/friends")
    @Operation(summary = "친구 랭킹 조회", description = "친구 목록을 뽀모도로 순으로 불러옵니다.")
    public List<FriendsRankRes>getFriendsRanking(@Parameter(hidden = true) @Jwt Long userId){
        return friendService.getFriendsRanking(userId);
    }

    @DeleteMapping("/friends")
    @Operation(summary = "친구 삭제", description = "선택한 친구를 삭제합니다.")
    public void deleteFriend(@Parameter(hidden = true) @Jwt Long userId,
                               String nickname){
        friendService.removeFriend(userId, nickname);
    }

}
