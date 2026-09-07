package com.example.study_spring_boot.service;

import com.example.study_spring_boot.controller.dto.PostResponse;
import com.example.study_spring_boot.controller.dto.UserResponse;
import com.example.study_spring_boot.domain.Post;
import com.example.study_spring_boot.domain.User;
import com.example.study_spring_boot.repository.PostRepository;
import com.example.study_spring_boot.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public UserService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    // READ
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            userResponses.add(new UserResponse(user.getId(), user.getName(), user.getEmail()));
        }
        return userResponses;
    }

    public List<PostResponse> getPostsByUserId(long userId) {
//        if (!userRepository.existsById(userId))
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        //List<Post> posts = postRepository.findByUserId(userId);
        List<Post> posts = user.getPosts();

        List<PostResponse> postResponses = new ArrayList<>();
        for (Post post : posts) {
            postResponses.add(new PostResponse(post.getUser().getId(), post.getId(), post.getTitle(), post.getContent()));
        }

        return postResponses;
    }

    public UserResponse getUser(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }

    // CREATE
    public void createUser(String name, String email) {
        User user = new User(name, email);
        userRepository.save(user);
    }

    // UPDATE
    @Transactional
    public void updateUser(long id, String name, String email) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        user.setName(name);
        user.setEmail(email);
    }

    // DELETE
    public void deleteUser(long id) {
        if (userRepository.findById(id).isPresent())
            userRepository.deleteById(id);
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}