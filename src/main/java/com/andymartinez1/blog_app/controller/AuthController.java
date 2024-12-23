package com.andymartinez1.blog_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.andymartinez1.blog_app.dto.RegistrationDto;
import com.andymartinez1.blog_app.entity.User;
import com.andymartinez1.blog_app.service.UserService;

import jakarta.validation.Valid;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "/register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDto user,
            BindingResult result,
            Model model) {
        User existingUser = userService.findByEmail(user.getEmail());
        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null, "There is already an account registered with that email.");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "/register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "/login";
    }

}
