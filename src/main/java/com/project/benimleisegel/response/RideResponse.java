package com.project.benimleisegel.response;

import com.project.benimleisegel.enums.RideStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RideResponse(
        Long id,
        UserResponse driver,
        String originCity,
        String originDistrict,
        String destinationCity,
        String destinationDistrict,
        LocalDateTime departTime,
        BigDecimal price,
        RideStatus status
) {
}
