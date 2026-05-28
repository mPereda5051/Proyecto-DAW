package com.jinbu.jinbu.service;

import com.jinbu.jinbu.entities.Comment;
import com.jinbu.jinbu.entities.Post;
import com.jinbu.jinbu.entities.User;
import com.jinbu.jinbu.exceptions.EntityNotFoundException;
import com.jinbu.jinbu.repository.CommentRepository;
import com.jinbu.jinbu.repository.PostRepository;
import com.jinbu.jinbu.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Service
public class CommentServiceImplementation implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

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
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostIdOrderByCreatedAtDesc(postId);
    }

    @Override
    public Comment createComment(Long postId, String content) {
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("El comentario no puede estar vacío");
        }
        if (content.length() > 500) {
            throw new IllegalArgumentException("El comentario no puede exceder los 500 caracteres");
        }

        String sanitizedContent = HtmlUtils.htmlEscape(content);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(0L, User.class));

        // Rate limit básico: 10 segundos entre comentarios
        List<Comment> userComments = commentRepository.findByPostIdOrderByCreatedAtDesc(postId);
        for (Comment c : userComments) {
            if (c.getUser() != null && c.getUser().getId().equals(user.getId())) {
                if (c.getCreatedAt() != null && c.getCreatedAt().isAfter(Instant.now().minusSeconds(10))) {
                    throw new RuntimeException("Debes esperar 10 segundos para comentar de nuevo");
                }
                break; // Solo comprobamos el más reciente
            }
        }

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException(postId, Post.class));

        Comment comment = new Comment();
        comment.setContent(sanitizedContent);
        comment.setUser(user);
        comment.setPost(post);

        return commentRepository.save(comment);
    }
}
