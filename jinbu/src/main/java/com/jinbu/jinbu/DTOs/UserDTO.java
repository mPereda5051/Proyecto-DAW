package com.jinbu.jinbu.DTOs;

public record UserDTO(
    Long id,
    String username,
    String email,
    String name,
    int followersCount,
    int followingCount,
    boolean isFollowing
) {
}
