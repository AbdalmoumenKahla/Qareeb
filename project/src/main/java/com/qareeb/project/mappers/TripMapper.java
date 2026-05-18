package com.qareeb.project.mappers;

import com.qareeb.project.dto.TripResponse;
import com.qareeb.project.models.Trip;

public class TripMapper {

    public static TripResponse toDTO(Trip trip) {

        return TripResponse.builder()
                .id(trip.getId())
                .type(trip.getType())
                .description(trip.getDescription())
                .pickupLocation(trip.getPickupLocation())
                .destinationLocation(trip.getDestinationLocation())
                .price(trip.getPrice())
                .status(trip.getStatus())
                .createdAt(trip.getCreatedAt())
                .userId(trip.getUser().getId())
                .userName(trip.getUser().getName())
                .build();
    }
}