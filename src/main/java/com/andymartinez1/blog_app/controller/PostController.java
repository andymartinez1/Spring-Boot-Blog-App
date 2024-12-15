package com.andymartinez1.blog_app.controller;

import com.andymartinez1.blog_app.dto.PostDto;
import com.andymartinez1.blog_app.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/admin/posts")
    public String getPosts(Model model) {
        List<PostDto> posts = postService.findAllPosts();
        model.addAttribute("posts", posts);
        return "/admin/posts";
    }

    @GetMapping("/admin/posts/newpost")
    public String createPost(Model model) {
        PostDto postDto = new PostDto();
        model.addAttribute("post", postDto);
        return "/admin/create_post";
    }

}
