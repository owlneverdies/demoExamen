package org.example.demex.controller;

import lombok.RequiredArgsConstructor;
import org.example.demex.model.User;
import org.example.demex.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/registration")
public class AuthController {
    private final UserService userService;

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String registerUser(User user) {
        userService.registration(user);
        return "redirect:/login";
    }
}
