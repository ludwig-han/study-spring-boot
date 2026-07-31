package com.example.study_spring_boot.controller;
import java.util.Collection;

import com.example.study_spring_boot.controller.dto.CreatePostRequest;
import com.example.study_spring_boot.controller.dto.UpdatePostRequest;
import com.example.study_spring_boot.domain.Post;

import com.example.study_spring_boot.service.PostService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public Collection<Post> getPosts(@RequestParam(required = false) String sort) {
        //if (sort.equals("desc")) {
        if ("desc".equals(sort)) {
            return postService.findAllDesc();
        }

        return postService.getPosts();
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable long id) {
        return postService.getPost(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@Valid @RequestBody CreatePostRequest request) {
        return postService.createPost(request.getTitle(), request.getContent());
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable long id, @Valid @RequestBody UpdatePostRequest request) {
        return postService.updatePost(id, request.getTitle(), request.getContent());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable long id) {
        postService.deletePost(id);
    }

    @GetMapping("/search")
    public Page<Post> searchPosts(
            @RequestParam String keyword,
            @RequestParam(required = false) int page,
            @RequestParam(required = false) int size
    ) {
        return postService.searchPosts(keyword, page, size);
    }

    @GetMapping("/page")
    public Page<Post> getPostPage(
            @RequestParam int page, @RequestParam int size, @RequestParam(required = false) String direction
    ) {
        return postService.getPostPage(page, size, direction);
    }
}
