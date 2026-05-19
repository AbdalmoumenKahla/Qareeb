package com.qareeb.project.services.servicesImpl;

import com.qareeb.project.dto.AuthResponse;
import com.qareeb.project.dto.CreateUserRequest;
import com.qareeb.project.dto.LoginRequest;
import com.qareeb.project.exceptions.ResourceNotFoundException;
import com.qareeb.project.models.User;
import com.qareeb.project.repositories.UserRepository;
import com.qareeb.project.security.JwtService;
import com.qareeb.project.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository
                .findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(token)
                .userId(user.getId())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
    @Override
    public AuthResponse register(CreateUserRequest request) {

        boolean exists = userRepository
                .findByPhoneNumber(request.getPhoneNumber())
                .isPresent();

        if (exists) {
            throw new RuntimeException("Phone number already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        User savedUser = userRepository.save(user);

        String token = jwtService.generateToken(savedUser);

        return AuthResponse.builder()
                .token(token)
                .userId(savedUser.getId())
                .name(savedUser.getName())
                .phoneNumber(savedUser.getPhoneNumber())
                .build();
    }
}