package com.jinbu.jinbu.mappers;

import com.jinbu.jinbu.DTOs.PostDTO;
import com.jinbu.jinbu.entities.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public PostDTO toDTO(Post post) {
        return new PostDTO(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getUser().getId(),
                post.getUser().getUsername(),
                post.getLikes()
        );
    }
}
