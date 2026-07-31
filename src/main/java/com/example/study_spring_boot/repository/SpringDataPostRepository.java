package com.example.study_spring_boot.repository;

import com.example.study_spring_boot.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataPostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByTitleContainingOrContentContaining(String titleKeyword, String contentKeyword, Pageable pageable);

    List<Post> findAllByOrderByIdDesc();
}
