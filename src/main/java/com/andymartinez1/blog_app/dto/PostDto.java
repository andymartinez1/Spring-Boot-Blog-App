package com.andymartinez1.blog_app.dto;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {

    private Long id;

    @NotEmpty(message = "Title is required")
    private String title;
    private String url;

    @NotEmpty(message = "Content is required")
    private String content;

    @NotEmpty(message = "Short description is required")
    private String shortDescription;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Set<CommentDto> comments;

}
