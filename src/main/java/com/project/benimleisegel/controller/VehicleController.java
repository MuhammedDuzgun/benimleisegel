package com.project.benimleisegel.controller;

import com.project.benimleisegel.request.CreateVehicleRequest;
import com.project.benimleisegel.response.VehicleResponse;
import com.project.benimleisegel.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    //get users vehicle
    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponse> getUsersVehicle(@PathVariable("id") Long id) {
        return new ResponseEntity<>(vehicleService.getUsersVehicle(id), HttpStatus.OK);
    }

    //create vehicle
    @PostMapping
    public ResponseEntity<VehicleResponse> createVehicle(@RequestBody CreateVehicleRequest request) {
        return new ResponseEntity<>(vehicleService.createVehicle(request), HttpStatus.CREATED);
    }

    //delete users vehicle
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") Long id) {
        vehicleService.deleteUsersVehicle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
