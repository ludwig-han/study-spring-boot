package com.example.study_spring_boot.controller;

import com.example.study_spring_boot.controller.dto.CreateUserRequest;
import com.example.study_spring_boot.controller.dto.PostResponse;
import com.example.study_spring_boot.controller.dto.UpdateUserRequest;
import com.example.study_spring_boot.controller.dto.UserResponse;
import com.example.study_spring_boot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET
    @GetMapping
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    @GetMapping("/{userId}/posts")
    public List<PostResponse> getPostsByUserId(@PathVariable long userId) {
        return userService.getPostsByUserId(userId);
    }

    // POST
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createUser(@RequestBody CreateUserRequest userRequest) {
        userService.createUser(userRequest.getName(), userRequest.getEmail());
    }

    // PUT
    @PutMapping("/{id}")
    public void updateUser(@PathVariable long id, @RequestBody UpdateUserRequest userRequest) {
        userService.updateUser(id, userRequest.getName(), userRequest.getEmail());
    }

    // DELETE
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

}
