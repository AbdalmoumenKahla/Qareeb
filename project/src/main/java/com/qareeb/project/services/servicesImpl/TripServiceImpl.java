package com.qareeb.project.services.servicesImpl;

import com.qareeb.project.dto.CreateTripRequest;
import com.qareeb.project.dto.TripResponse;
import com.qareeb.project.enums.TripStatus;
import com.qareeb.project.enums.TripType;
import com.qareeb.project.exceptions.ResourceNotFoundException;
import com.qareeb.project.mappers.TripMapper;
import com.qareeb.project.models.Trip;
import com.qareeb.project.models.User;
import com.qareeb.project.repositories.TripRepository;
import com.qareeb.project.repositories.UserRepository;
import com.qareeb.project.services.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;
    private final UserRepository userRepository; // ✅ FIXED


    @Override
    public TripResponse createTrip(CreateTripRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Trip trip = Trip.builder()
                .type(request.getType())
                .description(request.getDescription())
                .pickupLocation(request.getPickupLocation())
                .destinationLocation(request.getDestinationLocation())
                .status(TripStatus.AVAILABLE)
                .user(user)
                .build();

        Trip saved = tripRepository.save(trip);

        return TripMapper.toDTO(saved);
    }

    @Override
    public List<TripResponse> getAllTrips() {
        return tripRepository.findAll()
                .stream()
                .map(TripMapper::toDTO)
                .toList();
    }
    @Override
    public TripResponse getTripById(Long id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found"));

        return TripMapper.toDTO(trip);
    }
    @Override
    public long getRequestCount() {
        return tripRepository.countByType(TripType.REQUEST);
    }

    @Override
    public long getOfferCount() {
        return tripRepository.countByType(TripType.OFFER);
    }
    @Override
    public void deleteTrip(Long id) {
        if (!tripRepository.existsById(id)) {
            throw new ResourceNotFoundException("Trip not found with id: " + id);
        }
        tripRepository.deleteById(id);
    }
}