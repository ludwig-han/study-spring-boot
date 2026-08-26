package com.example.study_spring_boot.controller.dto;

public class PostResponse {
    private long id;
    private String title;
    private String content;

    public PostResponse(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
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
}
