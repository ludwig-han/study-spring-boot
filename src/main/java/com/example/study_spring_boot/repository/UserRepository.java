package com.example.study_spring_boot.repository;

import com.example.study_spring_boot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
