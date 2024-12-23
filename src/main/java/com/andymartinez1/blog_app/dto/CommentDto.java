package com.andymartinez1.blog_app.dto;

import java.time.LocalDateTime;

import com.andymartinez1.blog_app.entity.Post;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {

    private Long id;

    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Email is required")
    @Email
    private String email;

    @NotEmpty(message = "Content is required")
    private String content;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    private Post post;
}
