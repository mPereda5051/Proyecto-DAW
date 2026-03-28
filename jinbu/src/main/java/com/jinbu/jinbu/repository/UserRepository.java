package com.jinbu.jinbu.repository;

import com.jinbu.jinbu.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    User findByUsername(String username);
}
