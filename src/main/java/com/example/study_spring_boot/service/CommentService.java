package com.example.study_spring_boot.service;

import com.example.study_spring_boot.controller.dto.CommentResponse;
import com.example.study_spring_boot.domain.Comment;
import com.example.study_spring_boot.domain.Post;
import com.example.study_spring_boot.domain.User;
import com.example.study_spring_boot.repository.CommentRepository;
import com.example.study_spring_boot.repository.PostRepository;
import com.example.study_spring_boot.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    // READ
    public List<CommentResponse> getComments() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentResponse> commentResponses = new ArrayList<>();
        for (Comment comment: comments) {
            commentResponses.add(CommentResponse.from(comment));
        }
        return commentResponses;
    }

    public CommentResponse getComment(long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return CommentResponse.from(comment);
    }

    public List<CommentResponse> getCommentsByUserId(long userId) {
        //User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!userRepository.existsById(userId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        List<Comment> comments = commentRepository.findByUserId(userId);
        List<CommentResponse> commentResponses = new ArrayList<>();
        for (Comment comment : comments) {
            commentResponses.add(CommentResponse.from(comment));
        }
        return commentResponses;
    }

    public List<CommentResponse> getCommentsByPostId(long postId) {
        //Post post = postRepository.findById(postId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!postRepository.existsById(postId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        List<Comment> comments = commentRepository.findByPostId(postId);
        List<CommentResponse> commentResponses = new ArrayList<>();
        for (Comment comment : comments) {
            commentResponses.add(CommentResponse.from(comment));
        }
        return commentResponses;
    }

    // CREATE
    public void createComment(long userId, long postId, String content) {
//        if (!userRepository.existsById(userId) || !postRepository.existsById(postId))
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Comment comment = new Comment(user, post, content);
        commentRepository.save(comment);
    }

    // UPDATE
    @Transactional
    public void updateComment(long id, String content) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        comment.setContent(content);
    }

    // DELETE
    public void deleteComment(long id) {
        if (commentRepository.existsById(id))
            commentRepository.deleteById(id);
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
