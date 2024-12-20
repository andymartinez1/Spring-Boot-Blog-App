package com.andymartinez1.blog_app.service;

import com.andymartinez1.blog_app.dto.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> findAllPosts();

    void createPost(PostDto postDto);

    PostDto findPostById(Long postId);

    void updatePost(PostDto postDto);

    void deletePost(Long postId);

}
