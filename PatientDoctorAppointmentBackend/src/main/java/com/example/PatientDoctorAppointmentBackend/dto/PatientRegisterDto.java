package com.example.PatientDoctorAppointmentBackend.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientRegisterDto {
    private Long patientId;
    @NotEmpty(message = "name cannot be empty")
    @NotNull(message = "name cannot be null")
    private String name;
    @NotEmpty(message = "username cannot be empty")
    @NotNull(message = "username cannot be null")
    private String username;
    @NotEmpty(message = "password cannot be empty")
    @NotNull(message = "password cannot be null")
    private String password;
    @NotEmpty(message = "email cannot be empty")
    @NotNull(message = "email cannot be null")
    private String email;
    @NotEmpty(message = "gender cannot be empty")
    @NotNull(message = "gender cannot be null")
    private String gender;
    @NotEmpty(message = "contact cannot be empty")
    @NotNull(message = "contact cannot be null")
    private String contactNumber;
}
