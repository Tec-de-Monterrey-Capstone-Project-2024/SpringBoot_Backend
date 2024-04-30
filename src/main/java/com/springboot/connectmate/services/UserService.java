package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.User.UserDTO;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(long id);
    UserDTO updateUser(long id, UserDTO userDTO);
    UserDTO patchUser(long id, Map<String, Object> fields);
    void deleteUser(long id);
}
