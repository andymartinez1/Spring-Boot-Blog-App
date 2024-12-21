package com.andymartinez1.blog_app.service.impl;

import com.andymartinez1.blog_app.dto.CommentDto;
import com.andymartinez1.blog_app.entity.Comment;
import com.andymartinez1.blog_app.entity.Post;
import com.andymartinez1.blog_app.mapper.CommentMapper;
import com.andymartinez1.blog_app.repository.CommentRepository;
import com.andymartinez1.blog_app.repository.PostRepository;
import com.andymartinez1.blog_app.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void createComment(String postUrl, CommentDto commentDto) {
        Post post = postRepository.findByUrl(postUrl).get();
        Comment comment = CommentMapper.mapToComment(commentDto);
        comment.setPost(post);
        commentRepository.save(comment);

    }

    @Override
    public List<CommentDto> findAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(CommentMapper::mapToCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

}
