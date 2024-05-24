package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.UserDTO;
import com.springboot.connectmate.models.User;
import com.springboot.connectmate.repositories.UserRepository;
import com.springboot.connectmate.services.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public void register(UserDTO userDto) {
        User user = mapper.map(userDto, User.class);
        userRepository.save(user);
    }
}
