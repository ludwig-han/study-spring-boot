package com.example.study_spring_boot.controller;
import java.util.Collection;

import com.example.study_spring_boot.controller.dto.CreatePostRequest;
import com.example.study_spring_boot.controller.dto.PostResponse;
import com.example.study_spring_boot.controller.dto.UpdatePostRequest;
//import com.example.study_spring_boot.domain.Post; => Controller는 이제 Post 객체를 몰라도 된다.
// 즉, DB Entity를 몰라도 API 처리가 가능하다.

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
    public Collection<PostResponse> getPosts(@RequestParam(required = false) String direction) {
        //if (sort.equals("desc")) {
        if ("desc".equals(direction)) {
            return postService.findAllDesc();
        }

        return postService.getPosts();
    }

    @GetMapping("/{id}")
    public PostResponse getPost(@PathVariable long id) {
        return postService.getPost(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse createPost(@Valid @RequestBody CreatePostRequest request) {
        return postService.createPost(request.getTitle(), request.getContent());
    }

    @PutMapping("/{id}")
    public PostResponse updatePost(@PathVariable long id, @Valid @RequestBody UpdatePostRequest request) {
        return postService.updatePost(id, request.getTitle(), request.getContent());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable long id) {
        postService.deletePost(id);
    }

    @GetMapping("/search")
    public Page<PostResponse> searchPosts(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String direction
    ) {
        return postService.searchPosts(keyword, page, size, direction);
    }

    @GetMapping("/page")
    public Page<PostResponse> getPostPage(
            @RequestParam int page, @RequestParam int size, @RequestParam(required = false) String direction
    ) {
        return postService.getPostPage(page, size, direction);
    }
}
