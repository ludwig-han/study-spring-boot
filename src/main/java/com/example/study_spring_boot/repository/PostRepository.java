package com.example.study_spring_boot.repository;

import com.example.study_spring_boot.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

@Repository
public class PostRepository {
    private final Map<Long, Post> posts = new HashMap<>();
    private long nextId = 3L;

    public PostRepository() {
        Post p1 = new Post(1L, "첫 글", "안녕하세요.");
        Post p2 = new Post(2L, "두 번째 글", "반갑습니다.");
        posts.put(p1.getId(), p1);
        posts.put(p2.getId(), p2);
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public Post findById(long id) {
        return posts.get(id);
    }

    public Post save(String title, String content) {
        Post post = new Post(nextId, title, content);
        posts.put(nextId, post);
        nextId++;
        return post;
    }

    public Post deleteById(long id) {
        return posts.remove(id);
    }
}
