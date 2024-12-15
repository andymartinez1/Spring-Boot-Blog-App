package com.andymartinez1.blog_app.repository;

import com.andymartinez1.blog_app.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByUrl(String url);

}
