package com.example.study_spring_boot.repository;

import com.example.study_spring_boot.domain.Post;

import java.util.Collection;

public interface PostRepository {
    Collection<Post> findAll();

    Post findById(Long id);

    Post save(String title, String content);

    Post deleteById(Long id);
}
