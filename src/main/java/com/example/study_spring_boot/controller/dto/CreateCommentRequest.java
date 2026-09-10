package com.example.study_spring_boot.controller.dto;

public class CreateCommentRequest {
    private String content;
    private long userId;
    private long postId;

    

    public CreateCommentRequest(String content, long userId, long postId) {
        this.content = content;
        this.userId = userId;
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public long getUserId() {
        return userId;
    }

    public long getPostId() {
        return postId;
    }
}
