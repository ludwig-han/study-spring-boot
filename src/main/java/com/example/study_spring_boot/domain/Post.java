package com.example.study_spring_boot.domain;

public class Post {
    private long id;
    private String title;
    private String content;

    public Post(){}

    public Post(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() {return content; }

    public void setId(long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
