package com.andymartinez1.blog_app.service.impl;

import java.util.Collections;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.andymartinez1.blog_app.dto.RegistrationDto;
import com.andymartinez1.blog_app.entity.Role;
import com.andymartinez1.blog_app.entity.User;
import com.andymartinez1.blog_app.repository.RoleRepository;
import com.andymartinez1.blog_app.repository.UserRepository;
import com.andymartinez1.blog_app.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setName(registrationDto.getFirstName() + " " + registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_GUEST");

        user.setRoles(Collections.singletonList(role));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
