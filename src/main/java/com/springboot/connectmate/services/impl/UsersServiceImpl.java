package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.models.Users;
import com.springboot.connectmate.repositories.UsersRepository;
import com.springboot.connectmate.services.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {
    private final ModelMapper mapper;
    private final UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(ModelMapper mapper, UsersRepository usersRepository){
        this.mapper = mapper;
        this.usersRepository = usersRepository;
    }

    @Override
    public Users findById(String id){
        Optional<Users> usersOptional = usersRepository.findById(id);

        return usersOptional.orElse(null);
    }
}
