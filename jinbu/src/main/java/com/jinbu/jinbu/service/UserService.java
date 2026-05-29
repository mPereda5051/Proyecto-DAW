package com.jinbu.jinbu.service;

import com.jinbu.jinbu.DTOs.UserDTO;
import com.jinbu.jinbu.entities.User;

import java.util.List;

public interface UserService {

    UserDTO getUser(Long id);
    UserDTO getUserByUsername(String username);
    User getUserEntityByUsername(String username);
    UserDTO saveUser(User user);
    void deleteUser(Long id);
    List<UserDTO> getUsers();
    void followUser(Long followerId, Long followedId);
    void toggleFollow(String followerUsername, String followedUsername);
    void updateProfile(String username, UserDTO userDTO);
    void changePassword(String username, String currentPassword, String newPassword);

}
