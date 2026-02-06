package com.jinbu.backend_jinbu.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jinbu.backend_jinbu.entities.User;
import com.jinbu.backend_jinbu.exception.UserNotFoundException;
import com.jinbu.backend_jinbu.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImplementation implements UserService {
    
    UserRepository userRepository;

    @Override
    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return unwrapUser(user, id);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getUsers() {
        return (List<User>)userRepository.findAll();
    }

    // Si el usuario no existe devuelve error, sino devuelve el usuario
    static User unwrapUser(Optional<User> entity, long id) {
        if (entity.isPresent()) return entity.get();
        else throw new UserNotFoundException(id);
    }
}
