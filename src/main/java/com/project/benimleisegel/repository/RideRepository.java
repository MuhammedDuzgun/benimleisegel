package com.project.benimleisegel.repository;

import com.project.benimleisegel.entity.Ride;
import com.project.benimleisegel.enums.RideStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findByDepartTimeAfterAndStatus(LocalDateTime departTime, RideStatus status);
}
