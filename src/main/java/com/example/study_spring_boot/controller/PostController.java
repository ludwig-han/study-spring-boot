package com.example.study_spring_boot.controller;

import com.example.study_spring_boot.controller.dto.CreatePostRequest;
import com.example.study_spring_boot.controller.dto.PostResponse;
import com.example.study_spring_boot.controller.dto.UpdatePostRequest;
import com.example.study_spring_boot.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostResponse> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{id}")
    public PostResponse getPost(@PathVariable long id) {
        return postService.getPost(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse createPost(@Valid @RequestBody CreatePostRequest request) {      // 변수 or dto 어케받더라;
        return postService.createPost(request.getTitle(), request.getContent());
    }

    @PutMapping("/{id}")
    public void updatePost(@PathVariable long id, @Valid @RequestBody UpdatePostRequest request) {
        postService.updatePost(id, request.getTitle(), request.getContent());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable long id) {
        postService.deletePost(id);
    }
}
