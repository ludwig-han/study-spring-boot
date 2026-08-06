package com.example.study_spring_boot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getPost() throws Exception {
        mockMvc.perform(get("/posts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void getPostNotFound() throws Exception {
        mockMvc.perform(get("/posts/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createPost() throws Exception {
        mockMvc.perform(post("/posts")
                .contentType("application/json")
                .content("""
                        {
                            "title": "테스트 제목",
                            "content": "테스트 내용"
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("테스트 제목"));

    }
}
