package com.qareeb.project.dto;

import com.qareeb.project.enums.TripType;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateTripRequest {

    @NotNull(message = "Trip type is required")
    private TripType type;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 300,
            message = "Description must be between 10 and 300 characters")
    private String description;

    @NotBlank(message = "Pickup location is required")
    @Size(min = 2, max = 100,
            message = "Pickup location must be between 2 and 100 characters")
    private String pickupLocation;

    @NotBlank(message = "Destination location is required")
    @Size(min = 2, max = 100,
            message = "Destination location must be between 2 and 100 characters")
    private String destinationLocation;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    private Double price;

    @NotNull(message = "User ID is required")
    @Positive(message = "User ID must be positive")
    private Long userId;
}