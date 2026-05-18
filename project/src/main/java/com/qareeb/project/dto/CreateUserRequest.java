package com.qareeb.project.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateUserRequest {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50,
            message = "Name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^05\\d{8}$",
            message = "Phone number must start with 05 and contain 10 digits"
    )
    private String phoneNumber;

    @NotBlank(message = "Password is required")
    @Size(min = 8,
            message = "Password must be at least 8 characters")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).*$",
            message = "Password must contain uppercase, lowercase, and number"
    )
    private String password;
}