package com.example.study_spring_boot.controller.dto;

public class UpdateCommentRequest {
    private String content;

    public UpdateCommentRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
