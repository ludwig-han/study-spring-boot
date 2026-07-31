package com.example.study_spring_boot.service;

import com.example.study_spring_boot.domain.Post;
import com.example.study_spring_boot.repository.PostRepository;
import com.example.study_spring_boot.exception.PostNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<Post> getPostPage(int page, int size, String direction) {
        Pageable pageable;
        if ("desc".equals(direction)) {
            pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        } else pageable = PageRequest.of(page, size);
        return postRepository.findAll(pageable);
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

    @Transactional
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

    public Page<Post> searchPosts(String keyword, int page, int size) {
        return postRepository.searchByKeyword(keyword, PageRequest.of(page, size));
    }

    public Collection<Post> findAllDesc() {
        return postRepository.findAllDesc();
    }
}