package com.example.study_spring_boot.controller;

import com.example.study_spring_boot.controller.dto.CommentResponse;
import com.example.study_spring_boot.repository.PostRepository;
import com.example.study_spring_boot.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Test
    void createComment_success() throws Exception {
        String json = """
                {
                "userId": 2,
                "postId": 11,
                "content": "new comment"
                }
                """;

        mockMvc.perform(
                post("/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        )
                .andExpect(status().isCreated());

        verify(commentService).createComment(2L, 11L, "new comment");
    }

    @Test
    void updateComment_success() throws Exception {
        String json = """
                {
                "content": "updated comment"
                }
                """;

        mockMvc.perform(
                put("/comments/11")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        )
                .andExpect(status().isOk());

        verify(commentService).updateComment(11L, "updated comment");
    }

    @Test
    void updateComment_notFound() throws Exception {
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND))
                .when(commentService).updateComment(999L, "updated comment");

        String json = """
                {
                "content": "updated comment"
                }
                """;

        mockMvc.perform(
                put("/comments/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        )
                .andExpect(status().isNotFound());
        verify(commentService).updateComment(999L, "updated comment");
    }

    @Test
    void deleteComment_success() throws Exception {
        mockMvc.perform(
                delete("/comments/2")
        )
                .andExpect(status().isNoContent());

        verify(commentService).deleteComment(2L);
    }

    @Test
    void deleteComment_notFound() throws Exception {
//        when(commentService.deleteComment(999L))
//                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND))
                .when(commentService).deleteComment(999L);

        mockMvc.perform(
                delete("/comments/999")
        )
                .andExpect(status().isNotFound());

        verify(commentService).deleteComment(999L);
    }
}
