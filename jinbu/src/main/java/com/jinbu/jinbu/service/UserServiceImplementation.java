package com.jinbu.jinbu.service;

import com.jinbu.jinbu.DTOs.UserDTO;
import com.jinbu.jinbu.entities.User;
import com.jinbu.jinbu.exceptions.EntityNotFoundException;
import com.jinbu.jinbu.mappers.UserMapper;
import com.jinbu.jinbu.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Transactional
@Service
public class UserServiceImplementation implements UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    @Override
    public UserDTO getUser(Long id) {
        return userMapper.toDTO(userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, User.class)));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDTO saveUser(User user) {
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

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, User.class));
    }
}
