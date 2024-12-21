package com.andymartinez1.blog_app.repository;

import com.andymartinez1.blog_app.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
