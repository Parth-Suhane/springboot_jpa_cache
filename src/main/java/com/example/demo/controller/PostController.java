package com.example.demo.controller;

import com.example.demo.domain.*;
import com.example.demo.dto.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;
    private final UserRepository userRepository;

    public PostController(PostService p, UserRepository u) {
        this.postService = p;
        this.userRepository = u;
    }

    @GetMapping
    public List<PostSummaryDto> list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return postService.getPostSummaries(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return postService.getPostWithAuthor(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestParam String title, @RequestParam String body, @RequestParam String authorEmail) {
        User u = userRepository.findByEmail(authorEmail).orElseGet(() -> userRepository.save(new User("Unknown", authorEmail)));
        Post p = new Post(title, body, u);
        return ResponseEntity.ok(postService.createPost(p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}