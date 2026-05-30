package com.qareeb.project.dto;

import com.qareeb.project.enums.TripStatus;
import com.qareeb.project.enums.TripType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TripResponse {

    private Long id;
    private TripType type;
    private String description;
    private String pickupLocation;
    private String destinationLocation;
    private TripStatus status;
    private LocalDateTime createdAt;
    private Long userId;
    private String userName;
}