package com.example.study_spring_boot.repository;

import com.example.study_spring_boot.domain.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

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
    public Page<Post> findAll(Pageable pageable) {
        return springDataPostRepository.findAll(pageable);
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

    @Override
    public Page<Post> searchByKeyword(String keyword, Pageable pageable) {
        return springDataPostRepository.findByTitleContainingOrContentContaining(keyword, keyword, pageable);
    }

    @Override
    public Collection<Post> findAllDesc() {
        return springDataPostRepository.findAllByOrderByIdDesc();
    }
}
