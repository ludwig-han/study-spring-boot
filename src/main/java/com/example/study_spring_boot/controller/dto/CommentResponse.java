package com.example.study_spring_boot.controller.dto;

import com.example.study_spring_boot.domain.Comment;
import com.example.study_spring_boot.domain.Post;
import com.example.study_spring_boot.domain.User;
import com.example.study_spring_boot.repository.CommentRepository;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CommentResponse {
    private long id;
    private String content;
    private long userId;
    private long postId;

    public CommentResponse(long id, String content, long userId, long postId) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.postId = postId;
    }

    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
                comment.getId(), comment.getContent(), comment.getUser().getId(), comment.getPost().getId()
        );
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public long getPostId() {
        return postId;
    }
}
