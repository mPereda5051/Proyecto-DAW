package com.jinbu.jinbu.web;

import com.jinbu.jinbu.DTOs.UserDTO;
import com.jinbu.jinbu.entities.User;
import com.jinbu.jinbu.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody @Valid User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @PutMapping("/{followerId}/followUser/{followedId}")
    public ResponseEntity<HttpStatus> followUser(@PathVariable Long followerId, @PathVariable Long followedId) {
        userService.followUser(followerId, followedId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}