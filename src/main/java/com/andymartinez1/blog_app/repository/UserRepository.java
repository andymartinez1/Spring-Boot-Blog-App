package com.andymartinez1.blog_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andymartinez1.blog_app.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
