package com.example.study_spring_boot.controller.dto;

public class UpdateCommentRequest {
    private long id;
    private String content;
    private long userId;
    private long postId;

    public UpdateCommentRequest(long id, String content, long userId, long postId) {
        this.id = id;
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

    public long getId() {
        return id;
    }

    public long getPostId() {
        return postId;
    }
}
