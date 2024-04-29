package com.example.PatientDoctorAppointmentBackend.dto;

import com.example.PatientDoctorAppointmentBackend.entity.Doctor;
import com.example.PatientDoctorAppointmentBackend.entity.Patient;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {
    private Long appointmentId;
    @NotEmpty(message = "dateTime should not be empty")
    private LocalDateTime appointmentDateTime;
    private String appointmentStatus;
    private DoctorRegisterDto doctor;
    private PatientRegisterDto patient;
    @NotEmpty(message = "doctorId need not be empty")
    private Long doctorId;
    @NotEmpty(message = "patientId need not be empty")
    private Long patientId;

}
