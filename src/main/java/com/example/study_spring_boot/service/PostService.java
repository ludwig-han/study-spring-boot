package com.example.study_spring_boot.service;

import com.example.study_spring_boot.domain.Post;
import com.example.study_spring_boot.exception.PostNotFoundException;
import com.example.study_spring_boot.repository.PostRepository;

import org.springframework.stereotype.Service;


import java.util.Collection;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Collection<Post> getPosts () {
        return postRepository.findAll();
    }

    public Post getPost(long id) {
        Post post = postRepository.findById(id);
        if (post == null)
            throw new PostNotFoundException();
        return post;
    }

    public Post createPost(String title, String content){
        return postRepository.save(title, content);
    }

    public Post updatePost(long id, String title, String content) {
        Post post = postRepository.findById(id);
        if (post == null)
            throw new PostNotFoundException();
        post.update(title, content);
        return post;
    }

    public void deletePost(long id) {
        Post post = postRepository.deleteById(id);
        if (post == null)
            throw new PostNotFoundException();
    }
}
