package com.example.demo.dto;

import java.time.Instant;

public class PostSummaryDto {
    private Long id;
    private String title;
    private Instant createdAt;
    private Long authorId;
    private String authorName;
    private Long commentCount;

    public PostSummaryDto(Long id, String t, Instant c, Long aId, String aName, Long cnt) {
        this.id = id;
        this.title = t;
        this.createdAt = c;
        this.authorId = aId;
        this.authorName = aName;
        this.commentCount = cnt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Long getCommentCount() {
        return commentCount;
    }
}