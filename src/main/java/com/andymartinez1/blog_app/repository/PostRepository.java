package com.andymartinez1.blog_app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.andymartinez1.blog_app.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByUrl(String url);

    @Query("SELECT p FROM Post p WHERE p.title LIKE %?1% OR p.shortDescription LIKE %?1%")
    List<Post> searchPosts(String keyword);

    @Query(value = "SELECT * FROM Posts p WHERE p.created_by =:userId", nativeQuery = true)
    List<Post> findPostsByUser(Long userId);

}
