package com.project.benimleisegel.repository;

import com.project.benimleisegel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
