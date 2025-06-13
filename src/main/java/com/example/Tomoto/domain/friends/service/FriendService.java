package com.example.Tomoto.domain.friends.service;

import com.example.Tomoto.domain.friends.dto.request.AddFriendReq;
import com.example.Tomoto.domain.friends.dto.response.AddFriendRes;
import com.example.Tomoto.domain.friends.dto.response.FriendsRankRes;
import com.example.Tomoto.domain.friends.entity.Friend;
import com.example.Tomoto.domain.friends.repository.FriendsRepository;
import com.example.Tomoto.domain.user.entity.User;
import com.example.Tomoto.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FriendService {
    private final FriendsRepository friendsRepository;
    private final UserRepository userRepository;

    public void addFriend(Long userID, AddFriendReq req) {

        User friend = userRepository.findByNickname(req.friendName())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        Long friendId = friend.getUserId();

        boolean exists = friendsRepository.existsByFromIdAndToId(userID, friendId);
        if (exists){
            throw new IllegalArgumentException("이미 친구로 등록된 사용자입니다.");
        }
        Friend friendShip = Friend.create(userID, friendId);
        friendsRepository.save(friendShip);
    }

    public void removeFriend(Long userId, String friendName) {
        Long friendId = userRepository.findByNickname(friendName)
                .orElseThrow(() -> new IllegalArgumentException("해당 닉네임의 유저가 존재하지 않습니다."))
                .getUserId();

        Friend friend = friendsRepository
                .findByFromIdAndToIdOrFromIdAndToId(userId, friendId, friendId, userId)
                .orElseThrow(() -> new IllegalArgumentException("친구 관계가 존재하지 않습니다."));

        friendsRepository.delete(friend);
    }

    public List<FriendsRankRes> getFriendsRanking(Long userId) {
        return friendsRepository.findMutualFriendsRank(userId);
    }
}
