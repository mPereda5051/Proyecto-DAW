package com.jinbu.jinbu.service;

import com.jinbu.jinbu.DTOs.PostDTO;
import com.jinbu.jinbu.entities.Comment;
import com.jinbu.jinbu.entities.Photo;
import com.jinbu.jinbu.entities.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService {

    // Unir comentarios y post
    Comment getComment(Long id);
    void deleteComment(Long id);
    Page<Comment> retrieveComments(int pageNumber);
    void createComment(Comment comment);

}
