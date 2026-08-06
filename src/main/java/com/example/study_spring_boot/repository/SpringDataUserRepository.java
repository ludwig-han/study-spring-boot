package com.example.study_spring_boot.repository;

import com.example.study_spring_boot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
