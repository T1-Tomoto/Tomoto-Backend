package com.example.Tomoto.domain.friends.controller;

import com.example.Tomoto.domain.friends.dto.request.FriendShipReq;
import com.example.Tomoto.domain.friends.dto.response.FriendsRankRes;
import com.example.Tomoto.domain.friends.service.FriendService;
import com.example.Tomoto.domain.user.dto.response.AllUserInfoRes;
import com.example.Tomoto.domain.user.service.UserService;
import com.example.Tomoto.global.annotation.Jwt;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<FriendShipReq> addFriend(@Parameter(hidden = true) @Jwt Long userId, String friendName){
        friendService.addFriend(userId, friendName);
        return ResponseEntity.ok(new FriendShipReq());
    }

    @GetMapping("/friends")
    @Operation(summary = "친구 랭킹 조회", description = "친구 목록을 뽀모도로 순으로 불러옵니다.")
    public List<FriendsRankRes>getFriendsRanking(@Parameter(hidden = true) @Jwt Long userId){
        return friendService.getFriendsRanking(userId);
    }

//    @PostMapping("/friends/{friendId}")
//    @Operation(summary = "친구 삭제", description = "선택한 친구를 삭제합니다.")
//    public ResponseEntity<FriendShipReq> deleteFriend(@Parameter(hidden = true) @Jwt Long userId,
//                               @PathVariable Long friendId){
//        friendService.removeFriend(userId, friendId);
//        return ResponseEntity.ok(new FriendShipReq());
//    }


}
