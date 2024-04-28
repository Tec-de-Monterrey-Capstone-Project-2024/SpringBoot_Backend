package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.repositories.QueueRepository;
import com.springboot.connectmate.repositories.UserRepository;
import com.springboot.connectmate.services.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final ModelMapper mapper;
    private final UserRepository userRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }


}
