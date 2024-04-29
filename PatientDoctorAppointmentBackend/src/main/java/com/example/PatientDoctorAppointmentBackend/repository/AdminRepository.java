package com.example.PatientDoctorAppointmentBackend.repository;

import com.example.PatientDoctorAppointmentBackend.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findByUsername(String username);
    Boolean existsByEmail(String email);
    Optional<Admin> findByUsernameOrEmail(String username, String emailId);
    Boolean existsByUsername(String username);
}
