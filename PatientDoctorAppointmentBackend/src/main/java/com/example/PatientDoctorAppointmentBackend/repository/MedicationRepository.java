package com.example.PatientDoctorAppointmentBackend.repository;

import com.example.PatientDoctorAppointmentBackend.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication,Long> {
}
