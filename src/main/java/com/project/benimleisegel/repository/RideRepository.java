package com.project.benimleisegel.repository;

import com.project.benimleisegel.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride, Long> {
}
