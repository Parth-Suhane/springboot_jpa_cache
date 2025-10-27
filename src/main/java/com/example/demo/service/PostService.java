package com.example.demo.service;

import com.example.demo.domain.Post;
import com.example.demo.dto.PostSummaryDto;
import com.example.demo.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository p, UserRepository u) {
        this.postRepository = p;
        this.userRepository = u;
    }

    @Cacheable(cacheNames = "postSummaries", key = "#page+'-'+#size")
    public List<PostSummaryDto> getPostSummaries(int page, int size) {
        return postRepository.findPostSummaries(PageRequest.of(page, size));
    }

    @Cacheable(cacheNames = "post", key = "#id")
    public Optional<Post> getPostWithAuthor(Long id) {
        return postRepository.findWithAuthorById(id);
    }

    @CacheEvict(cacheNames = {"postSummaries"}, allEntries = true)
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @CacheEvict(cacheNames = {"post", "postSummaries"}, allEntries = true)
    public Post updatePost(Post post) {
        return postRepository.save(post);
    }

    @CacheEvict(cacheNames = {"post", "postSummaries"}, allEntries = true)
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}