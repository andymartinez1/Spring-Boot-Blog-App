package com.andymartinez1.blog_app.service;

import java.util.List;

import com.andymartinez1.blog_app.dto.CommentDto;

public interface CommentService {

    void createComment(String postUrl, CommentDto commentDto);

    List<CommentDto> findAllComments();

    void deleteComment(Long commentId);

    List<CommentDto> findCommentsByPost();
}
