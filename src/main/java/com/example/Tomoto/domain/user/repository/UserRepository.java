package com.example.Tomoto.domain.user.repository;

import com.example.Tomoto.domain.user.dto.response.AllUserInfoRes;
import com.example.Tomoto.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsById(String id);

    Optional<User> findById(String id);
    Optional<User> findByNickname(String nickname);

    @Query("SELECT new com.example.Tomoto.domain.user.dto.response.AllUserInfoRes(" +
            "u.userId, u.nickname, u.level, u.xp) " +
            "FROM User u ORDER BY u.level DESC")
    List<AllUserInfoRes> findAllUserDetails();
}
