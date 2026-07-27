package com.example.study_spring_boot.controller.dto;

public class UpdatePostRequest {
    private String title;
    private String content;

//    public UpdatePostRequest(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }

    public UpdatePostRequest() {}

    public String getTitle() { return title; }
    public String getContent() { return content; }

    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
}
