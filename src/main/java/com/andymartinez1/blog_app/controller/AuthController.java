package com.andymartinez1.blog_app.controller;

import com.andymartinez1.blog_app.dto.RegistrationDto;
import com.andymartinez1.blog_app.entity.User;
import com.andymartinez1.blog_app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
        User exisingUser = userService.findByEmail(user.getEmail());
        if (exisingUser != null && exisingUser.getEmail() != null && !exisingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null, "There is already an account registered with that email.");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "/register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }

}
