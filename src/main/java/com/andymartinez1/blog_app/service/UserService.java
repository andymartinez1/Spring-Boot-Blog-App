package com.andymartinez1.blog_app.service;

import com.andymartinez1.blog_app.dto.RegistrationDto;
import com.andymartinez1.blog_app.entity.User;

public interface UserService {

    void saveUser(RegistrationDto registrationDto);

    User findByEmail(String email);

}
