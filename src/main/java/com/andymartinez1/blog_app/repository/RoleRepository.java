package com.andymartinez1.blog_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andymartinez1.blog_app.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
