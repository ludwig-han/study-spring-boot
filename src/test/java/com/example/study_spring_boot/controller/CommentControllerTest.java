package com.example.study_spring_boot.controller;

import com.example.study_spring_boot.controller.dto.CommentResponse;
import com.example.study_spring_boot.repository.PostRepository;
import com.example.study_spring_boot.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CommentService commentService;

    @MockitoBean
    private PostRepository postRepository;

    @Test
    void getComment_success() throws Exception {
        // given
        CommentResponse response = new CommentResponse(1L, "test comment", 2L, 11L);

        when(commentService.getComment(1L))
                .thenReturn(response);

        // when + then
        mockMvc.perform(
                get("/comments/1")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.content").value("test comment"))
                .andExpect(jsonPath("$.userId").value(2))
                .andExpect(jsonPath("$.postId").value(11));
    }

    @Test
    void getComment_notFound() throws Exception {
        // given
        when(commentService.getComment(999L))
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        // when + then
        mockMvc.perform(
                get("/comments/999")
        )
                .andExpect(status().isNotFound());
    }
}
