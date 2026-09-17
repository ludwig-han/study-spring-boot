package com.example.study_spring_boot.service;

import com.example.study_spring_boot.controller.dto.CommentResponse;
import com.example.study_spring_boot.domain.Comment;
import com.example.study_spring_boot.domain.Post;
import com.example.study_spring_boot.domain.User;
import com.example.study_spring_boot.repository.CommentRepository;
import com.example.study_spring_boot.repository.PostRepository;
import com.example.study_spring_boot.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private CommentService commentService;

    @Test
    void getComment_success() {
        // given
        User user = mock(User.class);
        Post post = mock(Post.class);

        when(user.getId()).thenReturn(2L);
        when(post.getId()).thenReturn(11L);

        Comment comment = new Comment(user, post, "test comment");

        when(commentRepository.findById(1L))
                .thenReturn(Optional.of(comment));

        // when
        CommentResponse response = commentService.getComment(1L);

        // then
        assertEquals("test comment", response.getContent());
        assertEquals(2L, response.getUserId());
        assertEquals(11L, response.getPostId());
    }

    @Test
    void getComment_notFound() {
        // Given
        when(commentRepository.findById(999L))
                .thenReturn(Optional.empty());

        // When & Then
        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> commentService.getComment(999L)
        );

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    void createComment_success() {
        // Given
        User user = mock(User.class);
        Post post = mock(Post.class);

        when(userRepository.findById(2L))
                .thenReturn(Optional.of(user));
        when(postRepository.findById(11L))
                .thenReturn(Optional.of(post));

        // When
        commentService.createComment(user.getId(), post.getId(), "new comment");

        // Then
        verify(commentRepository).save(any(Comment.class));
        //assertEquals("new comment", );
    }

    @Test
    void createComment_userNotFound() {
        // Given
        when(userRepository.findById(999L))
                .thenReturn(Optional.empty());

        // When + Then
        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> commentService.createComment(999L, 11L, "new comment")
        );

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());

        verify(commentRepository, never())
                .save(any(Comment.class));
    }

    @Test
    void updateComment_success() {
        // Given
        User user = mock(User.class);
        Post post = mock(Post.class);

        Comment comment = new Comment(user, post, "old comment");

        when(commentRepository.findById(1L))
                .thenReturn(Optional.of(comment));

        // When
        commentService.updateComment(1L, "updated comment");

        // Then
        assertEquals("updated comment", comment.getContent());
    }

    @Test
    void updateComment_notFound() {
        // Given
        when(commentRepository.findById(1L))
                .thenReturn(Optional.empty());

        // when + then
        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> commentService.updateComment(1L, "updated comment")
                // 람다 쓴 이유: 지금 실행하지 말고 assertThrows() 에게 넘겨주려고
        );

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());

    }

    @Test
    void deleteComment_success() {
        // Given
        when(commentRepository.existsById(1L))
                .thenReturn(true);

        // When
        commentService.deleteComment(1L);

        // Then
        verify(commentRepository).deleteById(1L);
    }

    @Test
    void deleteComment_notFound() {
        // Given
        when(commentRepository.existsById(999L))
                .thenReturn(false);

        // When + Then
        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> commentService.deleteComment(999L)
        );

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());

        verify(commentRepository, never()).deleteById(999L);

    }

}
