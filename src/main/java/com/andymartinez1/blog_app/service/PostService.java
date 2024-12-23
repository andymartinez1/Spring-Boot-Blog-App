package com.andymartinez1.blog_app.service;

import java.util.List;

import com.andymartinez1.blog_app.dto.PostDto;

public interface PostService {

    List<PostDto> findAllPosts();

    List<PostDto> findPostsByUser();

    void createPost(PostDto postDto);

    PostDto findPostById(Long postId);

    void updatePost(PostDto postDto);

    void deletePost(Long postId);

    PostDto findPostByUrl(String postUrl);

    List<PostDto> searchPosts(String query);
}
