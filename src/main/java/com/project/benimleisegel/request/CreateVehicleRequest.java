package com.project.benimleisegel.request;

public record CreateVehicleRequest(
        String email,
        String plate,
        String brand,
        String model
) {
}
