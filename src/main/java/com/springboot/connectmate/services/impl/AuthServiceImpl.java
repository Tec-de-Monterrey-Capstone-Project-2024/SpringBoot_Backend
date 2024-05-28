package com.springboot.connectmate.services.impl;

import com.amazonaws.services.connect.model.User;
import com.amazonaws.services.connect.model.UserSummary;
import com.springboot.connectmate.dtos.User.RegisterUserFormDTO;
import com.springboot.connectmate.dtos.User.UserResponseDTO;
import com.springboot.connectmate.exceptions.ResourceNotFoundException;
import com.springboot.connectmate.repositories.UserRepository;
import com.springboot.connectmate.services.AmazonConnectService;
import com.springboot.connectmate.services.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AmazonConnectService amazonConnectService;
    private final ModelMapper mapper;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, AmazonConnectService amazonConnectService, ModelMapper mapper){
        this.userRepository = userRepository;
        this.amazonConnectService = amazonConnectService;
        this.mapper = mapper;
    }


    @Override
    public UserResponseDTO registerUser(RegisterUserFormDTO registerUserFormDTO) {

        // Get all users from the Amazon Connect instance
        List<UserSummary> users = amazonConnectService.listUsers(registerUserFormDTO.getInstanceId());

        // Iterate over each user
        for (UserSummary userSummary : users) {
            // Get the user details with User Summary Id
            User user = amazonConnectService.getUserDescription(registerUserFormDTO.getInstanceId(), userSummary.getId());

            // Check if the user email matches the given email in the form
            if (user.getIdentityInfo().getEmail().equalsIgnoreCase(registerUserFormDTO.getEmail())) {
                // Create a new user in the database with the form details
                com.springboot.connectmate.models.User newUser =
                        new com.springboot.connectmate.models.User(
                                registerUserFormDTO.getFirebaseId(), // Firebase ID
                                user.getId(), // Connect ID
                                registerUserFormDTO.getInstanceId() // Instance ID
                        );
                userRepository.save(newUser);

                // Map the user details to a UserResponseDTO response object
                UserResponseDTO userResponseDTO = mapper.map(user.getIdentityInfo(), UserResponseDTO.class);
                userResponseDTO.setConnectId(user.getId());
                userResponseDTO.setInstanceId(registerUserFormDTO.getInstanceId());
                userResponseDTO.setUsername(user.getUsername());

                return userResponseDTO;
            }
        }
        throw new ResourceNotFoundException(
                String.format("Connect User not found with email '%s' in instance '%s'. Please check your email or your instance.",
                registerUserFormDTO.getEmail(),
                registerUserFormDTO.getInstanceId())
        );

    }

}
