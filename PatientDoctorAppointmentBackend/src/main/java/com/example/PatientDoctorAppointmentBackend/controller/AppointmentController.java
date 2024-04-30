package com.example.PatientDoctorAppointmentBackend.controller;

import com.example.PatientDoctorAppointmentBackend.dto.AppointmentDto;
import com.example.PatientDoctorAppointmentBackend.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("api/appointment")
public class AppointmentController {
    private AppointmentService appointmentService;
    @PreAuthorize("hasRole(\"PATIENT\")")
    @PostMapping("book")
    public ResponseEntity<AppointmentDto> book(  @RequestBody AppointmentDto appointmentDto) {
        AppointmentDto savedAppointment = appointmentService.bookAppointment(appointmentDto);
        return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
    }

}
