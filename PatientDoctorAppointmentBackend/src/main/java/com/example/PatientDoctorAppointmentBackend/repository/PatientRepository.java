package com.example.PatientDoctorAppointmentBackend.repository;

import com.example.PatientDoctorAppointmentBackend.entity.Medication;
import com.example.PatientDoctorAppointmentBackend.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    Optional<Patient> findByUsername(String username);
    Boolean existsByEmail(String email);
    Optional<Patient> findByUsernameOrEmail(String username, String email);
    Boolean existsByUsername(String username);
}
