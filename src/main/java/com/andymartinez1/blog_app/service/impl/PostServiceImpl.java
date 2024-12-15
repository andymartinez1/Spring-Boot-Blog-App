package com.andymartinez1.blog_app.service.impl;

import com.andymartinez1.blog_app.dto.PostDto;
import com.andymartinez1.blog_app.entity.Post;
import com.andymartinez1.blog_app.mapper.PostMapper;
import com.andymartinez1.blog_app.repository.PostRepository;
import com.andymartinez1.blog_app.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostDto> findAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

}
