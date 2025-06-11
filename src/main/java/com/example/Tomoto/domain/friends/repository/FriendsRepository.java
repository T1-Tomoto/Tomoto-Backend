package com.example.Tomoto.domain.friends.repository;

import com.example.Tomoto.domain.friends.entity.Friend;
import com.example.Tomoto.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendsRepository extends JpaRepository<Friend,Long> {

}
