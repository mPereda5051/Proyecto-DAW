package com.jinbu.jinbu.service;

import com.jinbu.jinbu.DTOs.PostDTO;
import com.jinbu.jinbu.entities.Comment;
import com.jinbu.jinbu.exceptions.EntityNotFoundException;
import com.jinbu.jinbu.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CommentServiceImplementation implements CommentService {

    CommentRepository commentRepository;

    @Override
    public Comment getComment(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Comment.class));
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Page<Comment> retrieveComments(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 20);
        return commentRepository.findAll(pageable);
    }

    @Override
    public void createComment(Comment comment) {
        commentRepository.save(comment);
    }
}
