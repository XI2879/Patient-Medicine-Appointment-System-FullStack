package com.example.PatientDoctorAppointmentBackend.controller;

import com.example.PatientDoctorAppointmentBackend.dto.*;
import com.example.PatientDoctorAppointmentBackend.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("api/patient")
public class PatientController {
    private PatientService patientService;


    @PreAuthorize("hasAnyRole(\"ADMIN\", \"DOCTOR\")")
    @GetMapping("all")
    public ResponseEntity<List<PatientRegisterDto>> getAllPatients() {
        List<PatientRegisterDto> patientRegisterDtoList = patientService.viewAllPatients();
        return ResponseEntity.ok().body(patientRegisterDtoList);
    }
}
