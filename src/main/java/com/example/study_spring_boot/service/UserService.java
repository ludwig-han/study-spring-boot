package com.example.study_spring_boot.service;

import com.example.study_spring_boot.controller.dto.UserResponse;
import com.example.study_spring_boot.domain.User;
import com.example.study_spring_boot.exception.DuplicateEmailException;
import com.example.study_spring_boot.exception.UserNotFoundException;
import com.example.study_spring_boot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(String email, String password, String name) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new DuplicateEmailException();
        }

        User user = userRepository.save(email, password, name);
        return new UserResponse(user.getId(), user.getEmail(), user.getName());
    }

    public UserResponse getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        return new UserResponse(user.getId(), user.getEmail(), user.getName());
    }
}
