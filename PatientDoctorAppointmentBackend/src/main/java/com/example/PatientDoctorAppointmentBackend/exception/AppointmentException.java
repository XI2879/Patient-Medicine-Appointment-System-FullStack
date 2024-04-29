package com.example.PatientDoctorAppointmentBackend.exception;

public class AppointmentException extends RuntimeException {
    private String message;
    public AppointmentException(String message) {
        super(message);
    }
}
