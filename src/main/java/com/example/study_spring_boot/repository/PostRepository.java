package com.example.study_spring_boot.repository;

import com.example.study_spring_boot.domain.Post;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.Collection;

public interface PostRepository {
    Collection<Post> findAll();

    Page<Post> findAll(Pageable pageable);

    Post findById(Long id);

    Post save(String title, String content);

    Post deleteById(Long id);

    Page<Post> searchByKeyword(String keyword, Pageable pageable);

    Collection<Post> findAllDesc();
}
