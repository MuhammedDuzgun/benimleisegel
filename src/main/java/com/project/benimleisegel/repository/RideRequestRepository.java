package com.project.benimleisegel.repository;

import com.project.benimleisegel.entity.RideRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRequestRepository extends JpaRepository<RideRequest, Long> {
}
