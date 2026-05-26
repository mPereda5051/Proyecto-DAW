package com.jinbu.jinbu.web;

import com.jinbu.jinbu.DTOs.UserDTO;
import com.jinbu.jinbu.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
@Tag(name = "User Controller", description = "Management of user profiles and settings")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get current user profile")
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
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
}
