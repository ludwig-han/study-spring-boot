package com.example.study_spring_boot.controller;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

import org.springframework.web.bind.annotation.*;

import com.example.study_spring_boot.domain.Post;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final Map<Long, Post> posts = new HashMap<>();
    private long nextId = 3L;

    public PostController() {
        Post p1 = new Post(1L, "첫 글", "안녕하세요.");
        Post p2 = new Post(2L, "두 번째 글", "반갑습니다.");
        posts.put(p1.getId(), p1);
        posts.put(p2.getId(), p2);
    }

    @GetMapping
    public Collection<Post> getPosts() {
        return posts.values();
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable long id) {
        return posts.get(id);
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        Post newPost = new Post(nextId, post.getTitle(), post.getContent());
        posts.put(nextId, newPost);
        nextId++;
        return newPost;
    }

}
