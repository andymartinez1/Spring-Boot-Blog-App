package com.andymartinez1.blog_app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {

    private Long id;
    private String title;
    private String url;
    private String content;
    private String shortDescription;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

}
