package com.example.study_spring_boot.controller;

import com.example.study_spring_boot.controller.dto.CommentResponse;
import com.example.study_spring_boot.controller.dto.CreateCommentRequest;
import com.example.study_spring_boot.controller.dto.UpdateCommentRequest;
import com.example.study_spring_boot.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // GET
    @GetMapping()
    public List<CommentResponse> getComments() {
        return commentService.getComments();
    }

    @GetMapping("/{id}")
    public CommentResponse getComment(@PathVariable long id) {
        return commentService.getComment(id);
    }

    @GetMapping("/user/{id}")
    public List<CommentResponse> getCommentsUserId(@PathVariable long id) {
        return commentService.getCommentsByUserId(id);
    }

    @GetMapping("/post/{id}")
    public List<CommentResponse> getCommentsPostId(@PathVariable long id) {
        return commentService.getCommentsByPostId(id);
    }

    // POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createComment(@RequestBody CreateCommentRequest commentRequest) {
        commentService.createComment(commentRequest.getUserId(), commentRequest.getPostId(), commentRequest.getContent());
    }

    // PUT
    @PutMapping("/{id}")
    public void updateComment(@RequestBody UpdateCommentRequest commentRequest, @PathVariable long id) {
        commentService.updateComment(id, commentRequest.getContent());
    }

    // DELETE
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable long id) {
        commentService.deleteComment(id);
    }
}
