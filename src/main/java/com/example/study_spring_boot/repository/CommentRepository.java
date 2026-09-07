package com.example.study_spring_boot.repository;

import com.example.study_spring_boot.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByUserId(long userId);
    List<Comment> findByPostId(long postId);
}
