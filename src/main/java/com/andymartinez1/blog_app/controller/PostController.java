package com.andymartinez1.blog_app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.andymartinez1.blog_app.dto.CommentDto;
import com.andymartinez1.blog_app.dto.PostDto;
import com.andymartinez1.blog_app.service.CommentService;
import com.andymartinez1.blog_app.service.PostService;
import com.andymartinez1.blog_app.util.ROLE;
import com.andymartinez1.blog_app.util.SecurityUtils;

import jakarta.validation.Valid;

@Controller
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    private static String getUrl(String postTitle) {
        String title = postTitle.trim().toLowerCase();
        String url = title.replaceAll("\\s+", "-");
        url = url.replaceAll("[^A-Za-z0-9]", "-");
        return url;
    }

    @GetMapping("/admin/posts")
    public String getPosts(Model model) {
        String role = SecurityUtils.getRole();
        List<PostDto> posts = null;

        if (ROLE.ROLE_ADMIN.name().equals(role)) {
            posts = postService.findAllPosts();
        } else {
            posts = postService.findPostsByUser();
        }

        model.addAttribute("posts", posts);
        return "/admin/posts";
    }

    @GetMapping("/admin/posts/comments")
    public String postComments(Model model) {
        String role = SecurityUtils.getRole();
        List<CommentDto> comments = null;

        if (ROLE.ROLE_ADMIN.name().equals(role)) {
            comments = commentService.findAllComments();
        } else {
            comments = commentService.findCommentsByPost();
        }
        model.addAttribute("comments", comments);
        return "/admin/comments";
    }

    @GetMapping("/admin/posts/comments/{commentId}")
    public String deleteComment(@PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentId);
        return "redirect:/admin/posts/comments";
    }

    @GetMapping("/admin/posts/newpost")
    public String newPostForm(Model model) {
        PostDto postDto = new PostDto();
        model.addAttribute("post", postDto);
        return "/admin/create_post";
    }

    @PostMapping("/admin/posts")
    public String createPost(@Valid @ModelAttribute("post") PostDto postDto,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("post", postDto);
            return "/admin/create_post";
        }
        postDto.setUrl(getUrl(postDto.getTitle()));
        postService.createPost(postDto);
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/posts/{postUrl}/view")
    public String viewPost(@PathVariable("postUrl") String postUrl,
            Model model) {
        PostDto postDto = postService.findPostByUrl(postUrl);
        model.addAttribute("post", postDto);
        return "admin/view_post";
    }

    @GetMapping("/admin/posts/{postId}/edit")
    public String editPostForm(@PathVariable("postId") Long postId,
            Model model) {
        PostDto postDto = postService.findPostById(postId);
        model.addAttribute("post", postDto);
        return "/admin/edit_post";
    }

    @PostMapping("/admin/posts/{postId}")
    public String updatePost(@PathVariable("postId") Long postId,
            @Valid @ModelAttribute("post") PostDto post,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("post", post);
            return "/admin/edit_post";
        }
        post.setId(postId);
        postService.updatePost(post);
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/posts/{postId}/delete")
    public String deletePost(@PathVariable("postId") Long postId) {
        postService.deletePost(postId);
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/posts/search")
    public String searchPosts(@RequestParam(value = "query") String query,
            Model model) {
        List<PostDto> posts = postService.searchPosts(query);
        model.addAttribute("posts", posts);
        return "/admin/posts";
    }

}
