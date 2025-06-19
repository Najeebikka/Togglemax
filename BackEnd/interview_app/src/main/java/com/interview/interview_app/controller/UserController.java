package com.interview.interview_app.controller;

import com.interview.interview_app.dto.UserRequestDTO;
import com.interview.interview_app.dto.UserResponseDTO;
import com.interview.interview_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    // Create a new user
    @PostMapping
    public UserResponseDTO createUser(@RequestBody UserRequestDTO dto) {
        return userService.createUser(dto);
    }

    // Get user by ID
    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable UUID id) {
        return userService.getUser(id);
    }

    // Get all users
    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    // Update a user
    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@PathVariable UUID id, @RequestBody UserRequestDTO dto) {
        return userService.updateUser(id, dto);
    }

    // Delete a user
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }
}
