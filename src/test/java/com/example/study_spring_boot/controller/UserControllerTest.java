package com.example.study_spring_boot.controller;

import com.example.study_spring_boot.controller.dto.UserResponse;
import com.example.study_spring_boot.repository.PostRepository;
import com.example.study_spring_boot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    UserService userService;

    @MockitoBean
    PostRepository postRepository;


//    @Test
//    void getUsers_success() throws Exception {
//        List<UserResponse> userResponses = new ArrayList<>();
//
//
//        mockMvc.perform(
//                get("/users")
//        )
//                .andExpect(status().isOk());
//
//        verify(userService).getUsers();     // 이것도 해야하나?
//    }

    @Test
    void getUser_success() throws Exception {
        // given
        UserResponse response = new UserResponse(2L, "test user", "test@email");
        when(userService.getUser(2L)).thenReturn(response);

        // when + then
        mockMvc.perform(
                get("/users/2")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.name").value("test user"))
                .andExpect(jsonPath("$.email").value("test@email"));
    }

    @Test
    void getUser_notFound() throws Exception {
        when(userService.getUser(999L))
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        mockMvc.perform(
                get("/users/999")
        )
                .andExpect(status().isNotFound());
    }

//    @Test
//    void getPostsByUserId() throws Exception {
//
//    }

    @Test
    void createUser_success() throws Exception {
        String json = """
                {
                "name": "new name",
                "email": "new@email"
                }
                """;

        mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        )
                .andExpect(status().isCreated());

        verify(userService).createUser("new name", "new@email");
    }

    @Test
    void updateUser_success() throws Exception {
        String json = """
                {
                "name": "new name",
                "email": "new@email"
                }
                """;

        mockMvc.perform(
                put("/users/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        )
                .andExpect(status().isOk());

        verify(userService).updateUser(2L, "new name", "new@email");
    }

    @Test
    void updateUser_notFound() throws Exception {
        String json = """
                {
                "name": "new name",
                "email": "new@email"
                }
                """;

        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND))
                .when(userService)
                .updateUser(999L, "new name", "new@email");

        mockMvc.perform(
                put("/users/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        )
                .andExpect(status().isNotFound());

        verify(userService).updateUser(999L, "new name", "new@email");
    }

    @Test
    void deleteUser_success() throws Exception {

        mockMvc.perform(
                delete("/users/11")
        )
                .andExpect(status().isNoContent());

        verify(userService).deleteUser(11L);
    }

    @Test
    void deleteUser_notFound() throws Exception {
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND))
                .when(userService)
                .deleteUser(999L);

        mockMvc.perform(
                delete("/users/999")
        )
                .andExpect(status().isNotFound());

        verify(userService).deleteUser(999L);
    }

}
