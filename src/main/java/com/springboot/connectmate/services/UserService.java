package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDto);
}
