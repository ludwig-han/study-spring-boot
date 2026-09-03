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

    // GET
    @GetMapping
    public List<PostResponse> getPosts(@RequestParam(required = false) String keyword,
                                       @RequestParam(required = false) Integer size,
                                       @RequestParam(required = false) Integer page,
                                       @RequestParam(required = false) String sort) {
        if (keyword != null)
            return postService.searchPosts(keyword);
        if (size != null && page != null)
            return postService.getPostsPage(page, size, sort);
        return postService.getPosts();
    }

    @GetMapping("/{id}")
    public PostResponse getPost(@PathVariable long id) {
        return postService.getPost(id);
    }

    // POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse createPost(@Valid @RequestBody CreatePostRequest request) {
        return postService.createPost(request.getUserId(), request.getTitle(), request.getContent());
    }

    // PUT
    @PutMapping("/{id}")
    public void updatePost(@PathVariable long id, @Valid @RequestBody UpdatePostRequest request) {
        postService.updatePost(id, request.getTitle(), request.getContent());
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable long id) {
        postService.deletePost(id);
    }
}
