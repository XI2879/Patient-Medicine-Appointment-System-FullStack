package com.example.PatientDoctorAppointmentBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminRegisterDto {
    private String name;
    private String username;
    private String email;
    private String password;
}
