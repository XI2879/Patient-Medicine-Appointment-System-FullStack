package com.example.PatientDoctorAppointmentBackend.service;

import com.example.PatientDoctorAppointmentBackend.dto.AppointmentDto;

public interface AppointmentService {
    AppointmentDto bookAppointment(AppointmentDto appointmentDto);

}
