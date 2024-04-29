package com.example.PatientDoctorAppointmentBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientLoginDto {
    private String username;
    private String password;
}
