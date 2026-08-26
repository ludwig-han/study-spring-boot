package com.example.study_spring_boot.controller.dto;

import jakarta.validation.constraints.NotBlank;

public class UpdatePostRequest {
    private long id;
    @NotBlank
    private String title;
    @NotBlank
    private String content;

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
