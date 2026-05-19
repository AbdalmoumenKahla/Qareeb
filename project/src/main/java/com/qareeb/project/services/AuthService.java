package com.qareeb.project.services;

import com.qareeb.project.dto.AuthResponse;
import com.qareeb.project.dto.CreateUserRequest;
import com.qareeb.project.dto.LoginRequest;

public interface AuthService {
    public AuthResponse login(LoginRequest request);

    public AuthResponse register(CreateUserRequest request);
}