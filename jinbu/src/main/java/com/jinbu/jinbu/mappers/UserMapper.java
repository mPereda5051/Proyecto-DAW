package com.jinbu.jinbu.mappers;

import com.jinbu.jinbu.DTOs.UserDTO;
import com.jinbu.jinbu.entities.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean isFollowing = user.getFollowers().stream()
                .anyMatch(follower -> follower.getUsername().equals(currentUsername));

        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getName(),
                user.getFollowers(),
                user.getFollowing(),
                user.getFollowers().size(),
                user.getFollowing().size(),
                isFollowing
        );
    }
}
