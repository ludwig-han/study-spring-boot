package com.example.study_spring_boot.repository;

import com.example.study_spring_boot.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitleContaining(String keyword);

    List<Post> findByUserId(Long id);
}
