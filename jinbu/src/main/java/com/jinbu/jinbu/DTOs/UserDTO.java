package com.jinbu.jinbu.DTOs;

import com.jinbu.jinbu.entities.User;

import java.util.Set;

public record UserDTO(
    Long id,
    String username,
    String email,
    String name,
    Set<User> followers,
    Set<User> following,
    int followersCount,
    int followingCount,
    boolean isFollowing
) {
}
