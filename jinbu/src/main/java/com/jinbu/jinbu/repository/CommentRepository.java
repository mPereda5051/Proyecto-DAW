package com.jinbu.jinbu.repository;

import com.jinbu.jinbu.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
