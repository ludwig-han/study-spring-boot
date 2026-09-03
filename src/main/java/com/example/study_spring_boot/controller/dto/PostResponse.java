package com.example.study_spring_boot.controller.dto;

public class PostResponse {
    private long id;
    private String title;
    private String content;
    private long userId;

    public PostResponse(long userId, long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public long getUserId() {
        return userId;
    }
}
