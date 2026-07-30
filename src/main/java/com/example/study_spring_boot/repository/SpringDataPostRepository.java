package com.example.study_spring_boot.repository;

import com.example.study_spring_boot.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataPostRepository extends JpaRepository<Post, Long> {
    
}
