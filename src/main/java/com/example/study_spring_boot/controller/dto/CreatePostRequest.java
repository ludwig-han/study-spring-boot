package com.example.study_spring_boot.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreatePostRequest {
    private long id;
    @NotBlank
    private String title;
    @NotBlank
    private String content;

    @NotNull
    @Positive
    private long userId;

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
