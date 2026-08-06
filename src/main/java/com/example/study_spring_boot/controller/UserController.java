package com.example.study_spring_boot.controller;
import com.example.study_spring_boot.domain.User;

import com.example.study_spring_boot.controller.dto.CreateUserRequest;
import com.example.study_spring_boot.controller.dto.UserResponse;
import com.example.study_spring_boot.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        return userService.createUser(
                createUserRequest.getEmail(),
                createUserRequest.getPassword(),
                createUserRequest.getName()
        );
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

}
