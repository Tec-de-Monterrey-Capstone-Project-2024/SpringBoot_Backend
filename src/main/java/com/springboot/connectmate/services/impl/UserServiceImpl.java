package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.repositories.UserRepository;
import com.springboot.connectmate.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper){
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

}
