package com.jinbu.jinbu.DTOs;

import com.jinbu.jinbu.entities.Photo;

public record PostDTO(
        Long id,
        String title,
        String content,
        Long userId,
        String username,
        Long likes
) {
}
