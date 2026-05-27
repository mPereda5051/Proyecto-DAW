package com.jinbu.jinbu.mappers;

import com.jinbu.jinbu.DTOs.PostDTO;
import com.jinbu.jinbu.entities.Post;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public PostDTO toDTO(Post post) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean likedByUser = post.getLikedByUsers().stream()
                .anyMatch(user -> user.getUsername().equals(currentUsername));

        return new PostDTO(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getUser().getId(),
                post.getUser().getUsername(),
                (long) post.getLikedByUsers().size(),
                likedByUser,
                post.getPhoto()
        );
    }
}
