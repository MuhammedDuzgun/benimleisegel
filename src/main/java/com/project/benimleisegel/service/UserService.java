package com.project.benimleisegel.service;

import com.project.benimleisegel.entity.User;
import com.project.benimleisegel.exception.ResourceAlreadyExistsException;
import com.project.benimleisegel.exception.ResourceNotFoundException;
import com.project.benimleisegel.mapper.UserMapper;
import com.project.benimleisegel.repository.UserRepository;
import com.project.benimleisegel.request.CreateUserRequest;
import com.project.benimleisegel.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    //get user by id
    public UserResponse getUserById(Long id) {
        User user =  userRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("User with id " + id + " not found")
        );

        return userMapper.mapToUserResponse(user);
    }

    //get all users
    public List<UserResponse> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::mapToUserResponse)
                .toList();
    }

    //create user
    @Transactional
    public UserResponse createUser(CreateUserRequest request) {
        //check email
        if (userRepository.existsByEmail(request.email())) {
            throw new ResourceAlreadyExistsException("User with email " + request.email() + " already exists");
        }

        //check phone number
        if (userRepository.existsByPhone(request.phone())) {
            throw new ResourceAlreadyExistsException("User with phone " + request.phone() + " already exists");
        }

        return  userMapper.mapToUserResponse
                (userRepository.save(userMapper.mapToUser(request)));
    }

    //delete user
    @Transactional
    public void deleteUserById(Long id) {
        if (!userRepository.findById(id).isPresent()) {
            throw new ResourceNotFoundException("User with id " + id + " not found");
        }
        userRepository.deleteById(id);
    }

}
