package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.User.UpdateUserDTO;
import com.springboot.connectmate.dtos.User.UserDTO;
import com.springboot.connectmate.dtos.User.UserInfoDTO;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(long id);
    UserDTO updateUser(long id, UserDTO userDTO);
    UserInfoDTO patchUser(long id, UpdateUserDTO updateUserDTO);
    void deleteUser(long id);


}
