package com.example.Tomoto.domain.friends.repository;

import com.example.Tomoto.domain.friends.dto.response.FriendsRankRes;
import com.example.Tomoto.domain.friends.entity.Friend;
import com.example.Tomoto.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FriendsRepository extends JpaRepository<Friend,Long> {
    @Query("""
    SELECT new com.example.Tomoto.domain.friends.dto.response.FriendsRankRes(u.nickname, u.totalPomo)
    FROM User u
    WHERE u.userId IN (
        SELECT f.toId FROM Friend f WHERE f.fromId = :userId
        UNION
        SELECT f.fromId FROM Friend f WHERE f.toId = :userId
    )
    ORDER BY u.totalPomo DESC
""")
    List<FriendsRankRes> findMutualFriendsRank(@Param("userId") Long userId);

    Optional<Friend> findByFromIdAndToIdOrFromIdAndToId(Long fromId1, Long toId1, Long fromId2, Long toId2);

    void deleteByFromIdAndToId(Long fromId, Long toId);

    void delete(Friend friend);

    boolean existsByFromIdAndToId(Long fromId, Long toId);

}
