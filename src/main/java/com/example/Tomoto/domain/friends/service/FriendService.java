package com.example.Tomoto.domain.friends.service;

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

    public void addFriend(Long userID, String friendName) {
        User friend = userRepository.findByNickname(friendName).get();
        Long friendId = friend.getUserId();
        Friend friendShip = Friend.create(userID, friendId);
        friendsRepository.save(friendShip);
    }

    public void removeFriend(Long userID, Long friendId) {

    }

    public List<FriendsRankRes> getFriendsRanking(Long userId) {
        return friendsRepository.findMutualFriendsRank(userId);
    }
}
