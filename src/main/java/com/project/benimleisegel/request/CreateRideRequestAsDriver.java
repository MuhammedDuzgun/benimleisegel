package com.project.benimleisegel.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateRideRequestAsDriver(
        String originCity,
        String originDistrict,
        String destinationCity,
        String destinationDistrict,
        LocalDateTime departTime,
        BigDecimal price
) {
}
