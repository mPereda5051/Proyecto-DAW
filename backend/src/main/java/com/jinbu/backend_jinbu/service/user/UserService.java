package com.jinbu.backend_jinbu.service.user;

import java.util.List;

import com.jinbu.backend_jinbu.entities.User;

public interface UserService {
    User getUser(Long id);
    User saveUser(User user);
    void deleteUser(Long id);
    List<User> getUsers();
}
