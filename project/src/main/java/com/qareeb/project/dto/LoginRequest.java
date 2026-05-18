package com.qareeb.project.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String phoneNumber;
    private String password;
}