package com.example.PatientDoctorAppointmentBackend.dto;

import com.example.PatientDoctorAppointmentBackend.entity.Patient;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicationDto {
    @NotEmpty(message = "name cannot be empty")
    @NotNull(message = "name cannot be null")
    private String name;
    @NotEmpty(message = "medication cannot be empty")
    @NotNull(message = "medication cannot be null")
    private String medication;
    @NotEmpty(message = "dosage cannot be empty")
    @NotNull(message = "dosage cannot be null")
    private String dosage;
    @NotEmpty(message = "days  cannot be empty")
    @NotNull(message = "days cannot be null")
    private Integer days;
    private PatientRegisterDto patient;
    @NotEmpty(message = "patientId cannot be empty")
    @NotNull(message = "patientId cannot be null")
    private Long patientId;
}
