package com.example.demo.repository;

import com.example.demo.domain.Post;
import com.example.demo.dto.PostSummaryDto;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

import java.util.*;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT new com.example.demo.dto.PostSummaryDto(p.id,p.title,p.createdAt,u.id,u.name,COUNT(c.id)) FROM Post p JOIN p.author u LEFT JOIN p.comments c GROUP BY p.id,u.id,u.name ORDER BY p.createdAt DESC")
    List<PostSummaryDto> findPostSummaries(Pageable pageable);

    @EntityGraph(attributePaths = {"author"})
    Optional<Post> findWithAuthorById(Long id);

    @Query(value = "SELECT author_id as authorId, COUNT(*) as postCount FROM posts GROUP BY author_id ORDER BY postCount DESC LIMIT :limit", nativeQuery = true)
    List<Object[]> findTopAuthorsByPostCount(@Param("limit") int limit);
}