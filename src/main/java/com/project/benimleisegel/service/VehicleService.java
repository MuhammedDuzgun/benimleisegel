package com.project.benimleisegel.service;

import com.project.benimleisegel.entity.User;
import com.project.benimleisegel.entity.Vehicle;
import com.project.benimleisegel.exception.ResourceAlreadyExistsException;
import com.project.benimleisegel.exception.ResourceNotFoundException;
import com.project.benimleisegel.mapper.VehicleMapper;
import com.project.benimleisegel.repository.UserRepository;
import com.project.benimleisegel.repository.VehicleRepository;
import com.project.benimleisegel.request.CreateVehicleRequest;
import com.project.benimleisegel.response.VehicleResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;
    private final VehicleMapper vehicleMapper;

    public VehicleService(VehicleRepository vehicleRepository,
                          UserRepository userRepository,
                          VehicleMapper vehicleMapper) {
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
        this.vehicleMapper = vehicleMapper;
    }


    //get users vehicle
    //todo: email CustomUserDetails nesnesinden gelmeli
    public VehicleResponse getUsersVehicle(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (user.getVehicle() == null) {
            throw new ResourceNotFoundException("User does not have a vehicle");
        }
        return vehicleMapper.mapToVehicleResponse(user.getVehicle());
    }

    //create vehicle
    //todo: email CustomUserDetails nesnesinden gelmeli
    @Transactional
    public VehicleResponse createVehicle(CreateVehicleRequest request) {
        //check plate number
        if (vehicleRepository.existsByPlate(request.plate())) {
            throw new ResourceAlreadyExistsException("Plate " + request.plate() + " already exists");
        }
        Vehicle vehicle = vehicleMapper.mapToVehicle(request);

        //get user
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new ResourceNotFoundException("User with email " + request.email() + " not found"));

        vehicleRepository.save(vehicle);

        //set user
        user.setVehicle(vehicle);
        userRepository.save(user);

        return vehicleMapper.mapToVehicleResponse(vehicle);
    }


    //delete users vehicle
    //todo: email CustomUserDetails nesnesinden gelmeli
    @Transactional
    public void deleteUsersVehicle(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));

        //kullaniciya ait arac kaydi var mi
        if (user.getVehicle() == null) {
            throw new ResourceNotFoundException("User does not have a vehicle");
        }

        //vehicle - user iliskisini kopar
        user.setVehicle(null);
        userRepository.save(user);
    }
}
