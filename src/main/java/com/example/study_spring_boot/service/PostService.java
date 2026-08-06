package com.example.study_spring_boot.service;

import com.example.study_spring_boot.controller.dto.PostResponse;
import com.example.study_spring_boot.domain.Post;
import com.example.study_spring_boot.repository.PostRepository;
import com.example.study_spring_boot.exception.PostNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collection;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Collection<PostResponse> getPosts () {
        Collection<Post> posts = postRepository.findAll();
        Collection<PostResponse> responses = new ArrayList<>();
        for (Post post : posts) {
            responses.add(new PostResponse(post));
        }
        return responses;
    }

    public Page<PostResponse> getPostPage(int page, int size, String direction) {
        Pageable pageable;
        if ("desc".equals(direction)) {
            pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        } else pageable = PageRequest.of(page, size);

        Page<Post> posts = postRepository.findAll(pageable);
        return posts.map(post -> new PostResponse(post));
    }


    public PostResponse getPost(long id) {
        Post post = postRepository.findById(id);
        if (post == null)
            throw new PostNotFoundException();
        return new PostResponse(post);
    }

    public PostResponse createPost(String title, String content){
        return new PostResponse(postRepository.save(title, content));
    }

    @Transactional
    public PostResponse updatePost(long id, String title, String content) {
        Post post = postRepository.findById(id);
        if (post == null)
            throw new PostNotFoundException();
        post.update(title, content);
        return new PostResponse(post);
    }

    public void deletePost(long id) {
        Post post = postRepository.deleteById(id);
        if (post == null)
            throw new PostNotFoundException();
    }

    public Page<PostResponse> searchPosts(String keyword, int page, int size, String direction) {
        Pageable pageable;
        if ("desc".equals(direction)) {
            pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        } else pageable = PageRequest.of(page, size);
        Page<Post> posts = postRepository.searchByKeyword(keyword, pageable);
        return posts.map(post -> new PostResponse(post));
    }

    public Collection<PostResponse> findAllDesc() {
        Collection<Post> posts = postRepository.findAllDesc();
        Collection<PostResponse> responses = new ArrayList<>();
        for (Post post : posts) {
            responses.add(new PostResponse(post));
        }
        return responses;
    }
}