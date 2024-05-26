package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.User.UserResponseDTO;

public interface UserService {

    UserResponseDTO getUserByFirebaseId(String firebaseId);

}
