package com.example.Tomoto.domain.user.service;

import com.example.Tomoto.domain.user.dto.request.LevelAndExpUpdateReq;
import com.example.Tomoto.domain.user.dto.request.UserLoginReq;
import com.example.Tomoto.domain.user.dto.request.UserRegisterReq;
import com.example.Tomoto.domain.user.dto.response.AllUserInfoRes;
import com.example.Tomoto.domain.user.dto.response.UserInfoRes;
import com.example.Tomoto.domain.user.dto.response.UserTokenRes;
import com.example.Tomoto.domain.user.entity.User;
import com.example.Tomoto.domain.user.repository.UserRepository;
import com.example.Tomoto.global.jwt.JwtProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public UserTokenRes register(UserRegisterReq req) {
        if(userRepository.existsById(req.getId())) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
        User user = User.create(req.getId(), req.getPassword(), req.getNickname());
        user = userRepository.save(user);

        String accessToken = jwtProvider.createAccessToken(user);
        return new UserTokenRes(accessToken);
    }

    public UserTokenRes login(UserLoginReq req) {
        User user = userRepository.findById(req.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if(!req.getPassword().equals(user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        String accessToken = jwtProvider.createAccessToken(user);
        return new UserTokenRes(accessToken);
    }

    public UserInfoRes settings(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        return new UserInfoRes(user.getId(), user.getNickname(), user.getLevel(), user.getXp(), user.getTotalPomo(), user.getChallenges(), user.getBio());
    }

    public List<AllUserInfoRes> getAllUserInfo() {
        return userRepository.findAllUserDetails();
    }

    @Transactional
    public void levelXpUp(Long userId, LevelAndExpUpdateReq req) {
        User user = userRepository.findById(userId).orElseThrow();
        user.updateXpAndLevel(req.level(), req.xp());
    }

    @Transactional
    public void updateChallenges(Long userId, List<Boolean> updatedChallenges) {
        if (updatedChallenges == null || updatedChallenges.size() != 13) {
            throw new IllegalArgumentException("챌린지 리스트는 13개의 요소가 필요합니다.");
        }

        User user = userRepository.findById(userId).orElseThrow();
        user.setChallenges(updatedChallenges);
    }

    @Transactional
    public void updateUserBio(Long userId, String newBio) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setBio(newBio);
        userRepository.save(user);
    }
}
