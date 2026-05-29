package com.jinbu.jinbu.service;

import com.jinbu.jinbu.DTOs.UserDTO;
import com.jinbu.jinbu.entities.User;
import com.jinbu.jinbu.exceptions.EntityNotFoundException;
import com.jinbu.jinbu.mappers.UserMapper;
import com.jinbu.jinbu.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Transactional
@Service
public class UserServiceImplementation implements UserService {

    UserRepository userRepository;
    UserMapper userMapper;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDTO getUser(Long id) {
        return userMapper.toDTO(userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, User.class)));
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return userMapper.toDTO(userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(0L, User.class)));
    }

    @Override
    public User getUserEntityByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(0L, User.class));
    }

    @Override
    public UserDTO saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDTO> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .toList();
    }

    @Override
    public void followUser(Long followerId, Long followedId) {
        User followerUser = getUserById(followerId);
        User followedUser = getUserById(followedId);

        followerUser.getFollowing().add(followedUser);
        followedUser.getFollowers().add(followerUser);
    }

    @Override
    public void toggleFollow(String followerUsername, String followedUsername) {
        User follower = getUserEntityByUsername(followerUsername);
        User followed = getUserEntityByUsername(followedUsername);

        if (follower.getFollowing().contains(followed)) {
            follower.getFollowing().remove(followed);
            followed.getFollowers().remove(follower);
        } else {
            follower.getFollowing().add(followed);
            followed.getFollowers().add(follower);
        }
        userRepository.save(follower);
        userRepository.save(followed);
    }

    @Override
    public void updateProfile(String username, UserDTO userDTO) {
        User user = getUserEntityByUsername(username);
        user.setName(userDTO.name());
        user.setUsername(userDTO.username());
        user.setEmail(userDTO.email());
        userRepository.save(user);
    }

    @Override
    public void changePassword(String username, String currentPassword, String newPassword) {
        User user = getUserEntityByUsername(username);
        if (!bCryptPasswordEncoder.matches(currentPassword, user.getPassword())) {
            throw new RuntimeException("Current password does not match");
        }
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, User.class));
    }
}
