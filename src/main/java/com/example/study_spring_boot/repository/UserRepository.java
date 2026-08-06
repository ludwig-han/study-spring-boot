package com.example.study_spring_boot.repository;

import com.example.study_spring_boot.domain.User;

import java.util.Optional;

public interface UserRepository {
    User save(String email, String password, String name);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
}
