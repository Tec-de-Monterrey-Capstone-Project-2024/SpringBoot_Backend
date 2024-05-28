package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.User.RegisterUserFormDTO;
import com.springboot.connectmate.dtos.User.UserResponseDTO;


public interface AuthService {
    UserResponseDTO registerUser(RegisterUserFormDTO registerUserFormDTO);
}
