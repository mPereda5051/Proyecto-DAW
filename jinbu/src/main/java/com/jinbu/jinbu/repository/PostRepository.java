package com.jinbu.jinbu.repository;

import com.jinbu.jinbu.DTOs.PostDTO;
import com.jinbu.jinbu.entities.Post;
import com.jinbu.jinbu.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Long userId);
}
