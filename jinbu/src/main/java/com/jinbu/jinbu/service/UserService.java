package com.jinbu.jinbu.service;

import com.jinbu.jinbu.DTOs.UserDTO;
import com.jinbu.jinbu.entities.User;

import java.util.List;

public interface UserService {

    UserDTO getUser(Long id);
    User getUserByUsername(String username);
    UserDTO saveUser(User user);
    void deleteUser(Long id);
    List<UserDTO> getUsers();
    void followUser(Long followerId, Long followedId);

}
