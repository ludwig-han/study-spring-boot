package com.example.study_spring_boot.controller.dto;

public class UserResponse {
    private long id;
    private String email;
    private String name;

    public UserResponse(long id, String name, String email) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
