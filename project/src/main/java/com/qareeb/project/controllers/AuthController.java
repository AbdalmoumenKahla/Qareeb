package com.qareeb.project.controllers;

import com.qareeb.project.dto.AuthResponse;
import com.qareeb.project.dto.CreateUserRequest;
import com.qareeb.project.dto.LoginRequest;
import com.qareeb.project.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request) {

        return ResponseEntity.ok(
                authService.login(request)
        );
    }
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody CreateUserRequest request){
        return ResponseEntity.ok(
                authService.register(request)
        );
    }

}