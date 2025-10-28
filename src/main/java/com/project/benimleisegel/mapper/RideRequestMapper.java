package com.project.benimleisegel.mapper;

import com.project.benimleisegel.entity.RideRequest;
import com.project.benimleisegel.response.RideRequestResponse;
import org.springframework.stereotype.Component;

@Component
public class RideRequestMapper {

    private final RideMapper rideMapper;

    public RideRequestMapper(RideMapper rideMapper) {
        this.rideMapper = rideMapper;
    }

    public RideRequestResponse mapToResponse(RideRequest rideRequest) {
        return new RideRequestResponse(rideRequest.getId(),
                rideMapper.mapToRideResponse(rideRequest.getRide()),
                rideRequest.getStatus());
    }

}
