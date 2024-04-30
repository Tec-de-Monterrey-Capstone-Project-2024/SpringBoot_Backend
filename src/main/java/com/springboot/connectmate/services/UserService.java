package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.User.UserDTO;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserInfoDTO getUserById(long id);
    UserInfoDTO updateUser(long id, UserInfoDTO userInfoDTO);
    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserInfoDTO getUserById(long id);
    UserInfoDTO updateUser(long id, UserInfoDTO userInfoDTO);
    UserDTO patchUser(long id, Map<String, Object> fields);
    void deleteUser(long id);
}
