package com.springboot.connectmate.services;

import com.springboot.connectmate.models.Users;

public interface UsersService {
    Users findById(String id);
}
