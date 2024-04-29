package com.example.PatientDoctorAppointmentBackend.service;

import com.example.PatientDoctorAppointmentBackend.dto.DoctorLoginDto;
import com.example.PatientDoctorAppointmentBackend.dto.DoctorRegisterDto;
import com.example.PatientDoctorAppointmentBackend.dto.JwtAuthResponse;

import java.util.List;

public interface DoctorService {

    List<DoctorRegisterDto> viewAllDoctors();
}
