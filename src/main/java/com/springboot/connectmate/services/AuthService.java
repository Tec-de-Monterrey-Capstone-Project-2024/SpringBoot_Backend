package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.UserDTO;

public interface AuthService {
    void register(UserDTO userDto);
}
