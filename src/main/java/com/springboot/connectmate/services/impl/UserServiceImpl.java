package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.OldDTOS.UserDTO;
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
    public UserDTO patchUser(long id, Map<String, Object> fields) {
        // Get User by ID
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        fields.forEach((key, value) -> {
            // Map fields to properties of User
            switch (key) {
                case "first_name":
                    user.setFirstName((String) value);
                    break;
                case "last_name":
                    user.setLastName((String) value);
                    break;
                case "email":
                    user.setEmail((String) value);
                    break;
                case "password":
                    user.setPassword((String) value);
                    break;

            }
        });

        User updatedUser = userRepository.save(user);

        // Convert to DTO and return (assuming there's a method to convert an entity to DTO)
        return convertEntityModelToDTO(updatedUser);
    }

    @Override
    public void deleteUser(long id) {
        // Get User by ID
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        // Delete User
        userRepository.delete(user);

    }

}
