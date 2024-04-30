package com.springboot.connectmate.controllers;

import com.springboot.connectmate.dtos.User.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.springboot.connectmate.services.UserService;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@Tag(
        name = "User REST API",
        description = "CRUD REST API for Users"
)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create User Rest API
    @Operation(
            summary = "Create User",
            description = "Creates a new User"
    )
    @ApiResponse(
            responseCode = "201",
            description = "User created successfully"
    )
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }

    // Get All Users Rest API
    @Operation(
            summary = "Get All Users",
            description = "Get All Users"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Users fetched successfully"
    )
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get User by ID Rest API
    @Operation(
            summary = "Get User by Id",
            description = "Get User by Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "User fetched successfully"
    )
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // Update User Rest API
    @Operation(
            summary = "Update User",
            description = "Update User by Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "User updated successfully"
    )
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable(name = "id") long id, @RequestBody UserDTO userDTO) {
        UserDTO userResponse = userService.updateUser(id, userDTO);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    // Partial Update User Rest API
    @Operation(
            summary = "Partial Update User",
            description = "Partially Update User by Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "User partially updated successfully"
    )
    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> patchUser(@PathVariable(name = "id") long id, @RequestBody Map<String, Object> fields) {
        UserDTO userResponse = userService.patchUser(id, fields);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PatchMapping ("/{id}/change relevant info")
    public ResponseEntity<UserInfoDTO> updateUserPartially(@PathVariable(name = "id") long id, @RequestBody UserInfoDTO UserInfoDTO) {
        UserDTO existingUser = userService.getUserById(id);
        if (existingUser == null) {
            throw new ResourceNotFoundException ("User not found");
        }

        existingUser.setFirstName(UserInfoDTO.getFirstName());
        existingUser.setLastName(UserInfoDTO.getLastName());

        UserDTO updatedUser = userService.updateUser(id, existingUser);

        UserInfoDTO userInfoResponse = convertToUserInfoDTO(updatedUser);
        return ResponseEntity.ok(userInfoResponse);
    }

    private UserInfoDTO convertToUserInfoDTO(UserDTO userDTO) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setFirstName(updatedUser.getFirstName());
        userInfoDTO.setLastName(updatedUser.getLastName());
        return userInfoDTO;
    }

    // Delete User Rest API
    @Operation(
            summary = "Delete User",
            description = "Delete User by Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "User deleted successfully"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

}
