package com.jinbu.jinbu.DTOs;

public record PostDTO(
        Long id,
        String title,
        String content,
        Long userId,
        String username,
        Long likes
) {
}
