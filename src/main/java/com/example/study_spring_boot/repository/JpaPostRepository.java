package com.example.study_spring_boot.repository;

import com.example.study_spring_boot.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class JpaPostRepository implements PostRepository {
    private final SpringDataPostRepository springDataPostRepository;

    public JpaPostRepository(SpringDataPostRepository springDataPostRepository) {
        this.springDataPostRepository = springDataPostRepository;
    }

    @Override
    public Collection<Post> findAll() {
        return springDataPostRepository.findAll();
    }

    @Override
    public Post findById(Long id) {
        return springDataPostRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Post save(String title, String content) {
        Post post = new Post(title, content);
        return springDataPostRepository.save(post);
    }

    @Override
    public Post deleteById(Long id) {
        Post post = findById(id);
        if (post != null)
            springDataPostRepository.delete(post);

        return post;
    }
}
