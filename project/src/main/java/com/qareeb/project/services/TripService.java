package com.qareeb.project.services;

import com.qareeb.project.dto.CreateTripRequest;
import com.qareeb.project.dto.TripResponse;
import com.qareeb.project.models.Trip;

import java.util.List;

public interface TripService {

    TripResponse createTrip(CreateTripRequest request);

    List<TripResponse> getAllTrips();

    TripResponse getTripById(Long id);

    long getRequestCount();

    long getOfferCount();

    void deleteTrip(Long id);
}