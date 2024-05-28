package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.User.RegisterUserFormDTO;
import com.springboot.connectmate.dtos.User.UserResponseDTO;
import com.springboot.connectmate.services.AuthService;
import com.springboot.connectmate.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/users")
@Tag(
        name = "Authentication REST API",
        description = "CRUD REST API for Authentication"
)
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @Autowired
    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }


    @ApiResponse(
            responseCode = "200",
            description = "Http Status 201 CREATED"
    )
    @Operation(
            summary = "Register User REST API",
            description = "Register User REST API is used to register a new user in the system "
    )
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody RegisterUserFormDTO registerUserFormDTO){
        return new ResponseEntity<>(authService.registerUser(registerUserFormDTO), HttpStatus.CREATED);
    }

    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @Operation(
            summary = "Login User REST API",
            description = "Login User REST API is used to retrieve the user information by Firebase ID"
    )
    @GetMapping("/login/{firebaseId}")
    public ResponseEntity<UserResponseDTO> login(@PathVariable(name = "firebaseId") String firebaseId){
        return ResponseEntity.ok(userService.getUserByFirebaseId(firebaseId));

    }

}
