package com.qareeb.project.services;

import com.qareeb.project.dto.CreateUserRequest;
import com.qareeb.project.dto.UserResponse;
import com.qareeb.project.models.User;

import java.util.List;

public interface UserService {

    UserResponse createUser(CreateUserRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    void deleteUser(Long id);
}