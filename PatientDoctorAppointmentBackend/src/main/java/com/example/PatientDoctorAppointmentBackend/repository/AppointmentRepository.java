package com.example.PatientDoctorAppointmentBackend.repository;

import com.example.PatientDoctorAppointmentBackend.entity.Appointment;
import com.example.PatientDoctorAppointmentBackend.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.Long;
import java.time.LocalDateTime;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    @Query("SELECT b FROM Appointment b WHERE b.doctor.doctorId = :doctorId AND b.appointmentDateTime = :appointmentDateTime")
    Optional<Appointment> findBydoctorIdAndAppointmentDateTime(@Param("doctorId") Long doctorId, @Param("appointmentDateTime") LocalDateTime appointmentDateTime);
}
