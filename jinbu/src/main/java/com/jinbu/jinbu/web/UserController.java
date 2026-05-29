package com.jinbu.jinbu.web;

import com.jinbu.jinbu.DTOs.UserDTO;
import com.jinbu.jinbu.mappers.UserMapper;
import com.jinbu.jinbu.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
@Tag(name = "User Controller", description = "Management of user profiles and settings")
public class UserController {

    private final UserService userService;
    UserMapper userMapper;

    @Operation(summary = "Get current user profile")
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }

    @GetMapping("{username}/followers")
    public ResponseEntity<List<UserDTO>> getFollowersByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userService.getUserByUsername(username).followers().
                stream().map(userMapper::toDTO).toList(), HttpStatus.OK);
    }

    @GetMapping("{username}/following")
    public ResponseEntity<List<UserDTO>> getFollowingsByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userService.getUserByUsername(username).following().
                stream().map(userMapper::toDTO).toList(), HttpStatus.OK);
    }



    @Operation(summary = "Update current user profile")
    @PutMapping("/me")
    public ResponseEntity<Void> updateProfile(@RequestBody UserDTO userDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.updateProfile(username, userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Change current user password")
    @PutMapping("/me/password")
    public ResponseEntity<Void> changePassword(@RequestBody Map<String, String> passwordData) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String currentPassword = passwordData.get("currentPassword");
        String newPassword = passwordData.get("newPassword");
        userService.changePassword(username, currentPassword, newPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Toggle follow/unfollow a user")
    @PostMapping("/follow/{username}")
    public ResponseEntity<Void> toggleFollow(@PathVariable String username) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.toggleFollow(currentUsername, username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
