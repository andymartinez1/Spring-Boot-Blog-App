package com.andymartinez1.blog_app.service;

import com.andymartinez1.blog_app.dto.CommentDto;

import java.util.List;

public interface CommentService {

    void createComment(String postUrl, CommentDto commentDto);

    List<CommentDto> findAllComments();

    void deleteComment(Long commentId);
}
