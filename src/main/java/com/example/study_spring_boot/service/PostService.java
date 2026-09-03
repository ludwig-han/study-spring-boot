package com.example.study_spring_boot.service;

import com.example.study_spring_boot.controller.dto.PostResponse;
import com.example.study_spring_boot.domain.Post;
import com.example.study_spring_boot.domain.User;
import com.example.study_spring_boot.repository.PostRepository;
import com.example.study_spring_boot.repository.UserRepository;
import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    // GET
    public List<PostResponse> getPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostResponse> postResponses = new ArrayList<>();
        for (Post post : posts) {
            postResponses.add(new PostResponse(post.getUser().getId(), post.getId(), post.getTitle(), post.getContent()));
        }

        return postResponses;
    }

    public List<PostResponse> getPostsPage(int page, int size, String sort) {
        Sort sortOption = null;
        if ("oldest".equals(sort))
            sortOption = Sort.by(Sort.Direction.ASC, "id");
        else sortOption = Sort.by(Sort.Direction.DESC, "id");

        Pageable pageable = PageRequest.of(page, size, sortOption);
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> postList = posts.getContent();
        List<PostResponse> postResponses = new ArrayList<>();
        for (Post post : postList) {
            postResponses.add(new PostResponse(post.getUser().getId(), post.getId(), post.getTitle(), post.getContent()));
        }

        return postResponses;
    }

    public List<PostResponse> searchPosts(String keyword) {
        List<Post> posts = postRepository.findByTitleContaining(keyword);
        List<PostResponse> postResponses = new ArrayList<>();
        for (Post post : posts) {
            postResponses.add(new PostResponse(post.getUser().getId(), post.getId(), post.getTitle(), post.getContent()));
        }
        return postResponses;
    }

    public PostResponse getPost(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new PostResponse(post.getUser().getId(), post.getId(), post.getTitle(), post.getContent());
    }

    // CREATE
    public PostResponse createPost(long userId, String title, String content) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Post post = new Post(user, title, content);
        postRepository.save(post);
        return new PostResponse(post.getUser().getId(), post.getId(), post.getTitle(), post.getContent());
    }

    // UPDATE
    @Transactional
    public void updatePost(long id, String title, String content) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        post.setTitle(title);
        post.setContent(content);
    }

    // DELETE
    public void deletePost(long id) {
        if (postRepository.findById(id).isPresent())
            postRepository.deleteById(id);
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
