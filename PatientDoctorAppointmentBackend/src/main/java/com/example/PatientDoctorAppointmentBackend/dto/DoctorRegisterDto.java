package com.example.PatientDoctorAppointmentBackend.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRegisterDto {
    private Long doctorId;
    @NotEmpty(message = "name cannot be empty")
    @NotNull(message = "name cannot be null")
    private String name;
    @NotEmpty(message = "username cannot be empty")
    @NotNull(message = "username cannot be null")
    private String username;
    @NotEmpty(message = "email cannot be empty")
    @NotNull(message = "email cannot be null")
    private String email;
    @NotEmpty(message = "password cannot be empty")
    @NotNull(message = "password cannot be null")
    private String password;
    @NotEmpty(message = "specialization cannot be empty")
    @NotNull(message = "specialization cannot be null")
    private String specialization;
}
