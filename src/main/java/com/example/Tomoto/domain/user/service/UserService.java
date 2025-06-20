package com.example.Tomoto.domain.user.service;

import com.example.Tomoto.domain.friends.dto.request.AddFriendReq;
import com.example.Tomoto.domain.friends.entity.Friend;
import com.example.Tomoto.domain.friends.repository.FriendsRepository;
import com.example.Tomoto.domain.friends.service.FriendService;
import com.example.Tomoto.domain.pomo.entity.Pomo;
import com.example.Tomoto.domain.pomo.repository.PomoRepository;
import com.example.Tomoto.domain.todo.entity.Todo;
import com.example.Tomoto.domain.todo.repository.TodoRepository;
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

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final FriendsRepository friendRepository;
    private final TodoRepository todoRepository;
    private final PomoRepository pomoRepository;
    private final FriendService friendService;

    public UserTokenRes register(UserRegisterReq req) {
        if(userRepository.existsById(req.getId())) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        // 더미 데이터와 함께 유저 생성
        User user = createUserWithDummyData(req.getId(), req.getPassword(), req.getNickname());

        String accessToken = jwtProvider.createAccessToken(user);
        return new UserTokenRes(accessToken);
    }

    private User createUserWithDummyData(String id, String password, String nickname) {
        // 1. 새 유저 생성 및 저장
        User newUser = User.create(id, password, nickname);
        newUser = userRepository.save(newUser);

        // 2. 더미 친구들 생성 및 저장 (이미 존재하지 않는 경우만)
        List<User> dummyFriends = createOrGetDummyFriends();

        createFriendships(newUser, dummyFriends);
        createDummyTodos(newUser);
        createDummyPomoHistory(newUser);

        return newUser;
    }

    private List<User> createOrGetDummyFriends() {
        List<String> friendNames = List.of("여운", "상희", "승연", "서윤", "시은");
        List<User> dummyFriends = new ArrayList<>();

        for (String name : friendNames) {
            String dummyId = "dummy_" + name.toLowerCase();
            User existingFriend = userRepository.findById(dummyId).orElse(null);

            if (existingFriend == null) {
                // 더미 친구가 없으면 새로 생성
                User dummyFriend = User.builder()
                        .id(dummyId)
                        .password("dummy123")
                        .nickname(name)
                        .createdAt(LocalDateTime.now().minusDays((long)(Math.random() * 30)))
                        .level((int)(Math.random() * 10) + 1)
                        .xp((int)(Math.random() * 1000))
                        .totalPomo((int)(Math.random() * 50) + 10)
                        .bio(name + "의 한줄소개입니다!")
                        .build();
                dummyFriend = userRepository.save(dummyFriend);
                dummyFriends.add(dummyFriend);
            } else {
                dummyFriends.add(existingFriend);
            }
        }
        return dummyFriends;
    }

    private void createFriendships(User newUser, List<User> dummyFriends) {
        for (User dummyFriend : dummyFriends) {
            AddFriendReq req = new AddFriendReq(dummyFriend.getNickname());
            friendService.addFriend(newUser.getUserId(), req);
        }
    }

    private void createDummyTodos(User user) {
        List<String> todoContents = List.of(
                "영어 단어 50개 외우기",
                "수학 문제집 2페이지 풀기",
                "독서 30분하기",
                "운동 1시간하기",
                "프로젝트 계획서 작성하기",
                "온라인 강의 1개 수강하기",
                "코딩 연습 1시간",
                "일기 쓰기",
                "명상 10분",
                "청소하기",
                "친구에게 연락하기",
                "요리하기",
                "영화 한 편 보기",
                "새로운 기술 공부하기",
                "블로그 글 작성하기",
                "사진 정리하기",
                "음악 듣기",
                "자기계발서 읽기",
                "운동 계획 세우기",
                "주간 목표 설정하기",
                "외국어 회화 연습",
                "가계부 정리하기",
                "이메일 정리하기",
                "독서 노트 작성하기",
                "새로운 레시피 시도하기"
        );

        for (int i = 0; i < 25; i++) { // 25개의 더미 Todo 생성
            String content = todoContents.get(i % todoContents.size());
            LocalDateTime dueDate = LocalDateTime.now().plusDays(i + 1);
            boolean completed = Math.random() < 0.3; // 30% 확률로 완료 상태

            Todo todo = Todo.builder()
                    .user(user)
                    .content(content)
                    .dueDate(dueDate)
                    .completed(completed)
                    .build();

            todoRepository.save(todo);
        }
    }

    private void createDummyPomoHistory(User user) {
        // 최근 3주간의 뽀모도로 히스토리 생성 (오늘 제외, 21일)
        for (int i = 1; i <= 21; i++) {
            LocalDateTime date = LocalDateTime.now().minusDays(i).withHour(9).withMinute(0).withSecond(0);
            int pomoCount = (int)(Math.random() * 8) + 1; // 1-8개 랜덤

            Pomo pomo = Pomo.builder()
                    .user(user)
                    .pomoNum(pomoCount)
                    .build();

            pomo.setCreatedAt(date);
            pomoRepository.save(pomo);
        }

        // 유저의 totalPomo 업데이트
        int totalPomos = pomoRepository.findByUser(user)
                .stream()
                .mapToInt(Pomo::getPomoNum)
                .sum();
        user.setTotalPomo(totalPomos);
        userRepository.save(user);
    }


    // 기존 메서드들은 그대로 유지
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
        if (updatedChallenges == null || updatedChallenges.size() != 11) {
            throw new IllegalArgumentException("챌린지 리스트는 11개의 요소가 필요합니다.");
        }

        User user = userRepository.findById(userId).orElseThrow();
        user.updateChallenges(updatedChallenges);
        userRepository.save(user);
    }

    @Transactional
    public void updateUserBio(Long userId, String newBio) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setBio(newBio);
        userRepository.save(user);
    }
}