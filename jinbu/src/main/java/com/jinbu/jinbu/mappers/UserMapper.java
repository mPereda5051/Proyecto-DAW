package com.jinbu.jinbu.mappers;

import com.jinbu.jinbu.DTOs.UserDTO;
import com.jinbu.jinbu.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }
}
