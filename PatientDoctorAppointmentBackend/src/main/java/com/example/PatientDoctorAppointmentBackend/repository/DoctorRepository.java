package com.example.PatientDoctorAppointmentBackend.repository;

import com.example.PatientDoctorAppointmentBackend.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository< Doctor,Long> {

    Optional<Doctor> findByUsername(String username);
    Boolean existsByEmail(String email);
    Optional<Doctor> findByUsernameOrEmail(String username, String email);
    Boolean existsByUsername(String username);
}
