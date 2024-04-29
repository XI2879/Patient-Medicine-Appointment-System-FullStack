package com.example.PatientDoctorAppointmentBackend.service;


import com.example.PatientDoctorAppointmentBackend.dto.*;

public interface AuthService {
    JwtAuthResponse loginAdmin(AdminLoginDto adminLoginDto);
    String resisterAdmin(AdminRegisterDto adminRegisterDto);
    String registerDoctor(DoctorRegisterDto doctorRegisterDto);
    JwtAuthResponse loginDoctor(DoctorLoginDto doctorLoginDto);
    String registerPatient(PatientRegisterDto patientRegisterDto);
    JwtAuthResponse loginPatient(PatientLoginDto patientLoginDto);
}
