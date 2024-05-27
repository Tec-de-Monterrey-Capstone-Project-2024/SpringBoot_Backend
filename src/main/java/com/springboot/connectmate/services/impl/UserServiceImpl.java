package com.springboot.connectmate.services.impl;



import com.springboot.connectmate.dtos.User.UserResponseDTO;
import com.springboot.connectmate.exceptions.ResourceNotFoundException;
import com.springboot.connectmate.models.User;
import com.springboot.connectmate.repositories.UserRepository;
import com.springboot.connectmate.services.AmazonConnectService;
import com.springboot.connectmate.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AmazonConnectService amazonConnectService;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AmazonConnectService amazonConnectService, ModelMapper mapper){
        this.userRepository = userRepository;
        this.amazonConnectService = amazonConnectService;
        this.mapper = mapper;
    }


    @Override
    public UserResponseDTO getUserByFirebaseId(String firebaseId) {
        // Get the user from the database
        User user = userRepository.findById(firebaseId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "firebase id", firebaseId));

        // Get the user details from Connect
        com.amazonaws.services.connect.model.User connectUser = amazonConnectService
                .getUserDescription(user.getInstanceId(), user.getConnectId());

        // Map the user details to the UserResponseDTO
        UserResponseDTO newUser = mapper.map(connectUser.getIdentityInfo(), UserResponseDTO.class);
        newUser.setConnectId(user.getConnectId());
        newUser.setInstanceId(user.getInstanceId());
        newUser.setUsername(connectUser.getUsername());
        return newUser;
    }

}
