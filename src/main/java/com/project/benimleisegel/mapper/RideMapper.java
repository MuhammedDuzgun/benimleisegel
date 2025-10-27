package com.project.benimleisegel.mapper;

import com.project.benimleisegel.entity.Ride;
import com.project.benimleisegel.request.CreateRideRequestAsDriver;
import com.project.benimleisegel.response.RideResponse;
import org.springframework.stereotype.Component;

@Component
public class RideMapper {

    private final UserMapper userMapper;

    public RideMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Ride mapToRide(CreateRideRequestAsDriver request) {
        Ride ride = new Ride();
        ride.setOriginCity(request.originCity());
        ride.setOriginDistrict(request.originDistrict());
        ride.setDestinationCity(request.destinationCity());
        ride.setDestinationDistrict(request.destinationDistrict());
        ride.setDepartTime(request.departTime());
        ride.setPrice(request.price());
        return ride;
    }

    public RideResponse mapToRideResponse(Ride ride) {
        RideResponse rideResponse = new RideResponse(
                ride.getId(),
                userMapper.mapToUserResponse(ride.getDriver()),
                ride.getOriginCity(),
                ride.getOriginDistrict(),
                ride.getDestinationCity(),
                ride.getDestinationDistrict(),
                ride.getDepartTime(),
                ride.getPrice(),
                ride.getStatus()
        );
        return rideResponse;
    }

}
