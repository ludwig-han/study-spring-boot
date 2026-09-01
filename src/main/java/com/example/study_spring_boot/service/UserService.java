package com.example.study_spring_boot.service;

import com.example.study_spring_boot.controller.dto.UserResponse;
import com.example.study_spring_boot.domain.User;
import com.example.study_spring_boot.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // READ
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            userResponses.add(new UserResponse(user.getId(), user.getName(), user.getEmail()));
        }
        return userResponses;
    }

    public UserResponse getUser(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }

    // CREATE
    public void createUser(String name, String email) {
        User user = new User(name, email);
        userRepository.save(user);
    }

    // UPDATE
    @Transactional
    public void updateUser(long id, String name, String email) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        user.setName(name);
        user.setEmail(email);
    }

    // DELETE
    public void deleteUser(long id) {
        if (userRepository.findById(id).isPresent())
            userRepository.deleteById(id);
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
