package com.example.study_spring_boot.controller;
import java.util.Collection;

import com.example.study_spring_boot.controller.dto.CreatePostRequest;
import com.example.study_spring_boot.controller.dto.UpdatePostRequest;
import com.example.study_spring_boot.domain.Post;

import com.example.study_spring_boot.service.PostService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public Collection<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable long id) {
        return postService.getPost(id);
    }

    @PostMapping
    public Post createPost(@RequestBody CreatePostRequest request) {
        return postService.createPost(request.getTitle(), request.getContent());
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable long id, @RequestBody UpdatePostRequest request) {
        return postService.updatePost(id, request.getTitle(), request.getContent());
    }

    @DeleteMapping("/{id}")
    public Post deletePost(@PathVariable long id) {
        return postService.deletePost(id);
    }
}
