package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.UserDTO;
import com.springboot.connectmate.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody UserDTO userDto) {
        authService.register(userDto);
    }
}