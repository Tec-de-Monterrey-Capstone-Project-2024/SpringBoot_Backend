package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.User.UpdateUserDTO;
import com.springboot.connectmate.dtos.User.UserDTO;
import com.springboot.connectmate.dtos.User.UserInfoDTO;
import com.springboot.connectmate.exceptions.ResourceNotFoundException;
import com.springboot.connectmate.models.User;
import com.springboot.connectmate.services.UserService;
import com.springboot.connectmate.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper mapper;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    // Convert Entity Model to DTO
    private UserDTO convertEntityModelToDTO(User user) {
        return mapper.map(user, UserDTO.class);
    }

    // Convert DTO to Entity Model
    private User convertDTOtoEntityModel(UserDTO userDTO) {
        return mapper.map(userDTO, User.class);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        // Convert DTO to Model
        User user = convertDTOtoEntityModel(userDTO);

        // Save Model to DB
        User newUser = userRepository.save(user);

        // Convert Model to DTO
        return convertEntityModelToDTO(newUser);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        // Get all Supervisors
        List<User> users = userRepository.findAll();
        // this lambda expression is more readable
        //return users.stream().map(supervisor -> mapToDTO(supervisor)).collect(Collectors.toList());
        // this method reference is more concise, both do the same thing
        return users.stream().map(this::convertEntityModelToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(long id) {
        // Get User by ID
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return convertEntityModelToDTO(user);
    }

    @Override
    public UserDTO updateUser(long id, UserDTO userDTO) {
        // Get User by ID
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        // Update User
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());

        // Save User
        User updatedUser = userRepository.save(user);

        // Convert Model To DTO
        return convertEntityModelToDTO(updatedUser);

    }

    @Override
    public UserInfoDTO patchUser(long id, UpdateUserDTO updateUserDTO) {
        // Get User by ID
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        // Patch Fields
        if (updateUserDTO.getFirstName() != null) {
            user.setFirstName(updateUserDTO.getFirstName());
        }
        if (updateUserDTO.getLastName() != null) {
            user.setLastName(updateUserDTO.getLastName());
        }
        if (updateUserDTO.getPassword() != null) {
            user.setPassword(updateUserDTO.getPassword()); // Ensure password is hashed if necessary
        }
        userRepository.save(user);

        return mapper.map(user, UserInfoDTO.class);
    }

    @Override
    public void deleteUser(long id) {
        // Get User by ID
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        // Delete User
        userRepository.delete(user);

    }

}
