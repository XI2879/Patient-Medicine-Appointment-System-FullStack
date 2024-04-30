package com.example.PatientDoctorAppointmentBackend.controller;

import com.example.PatientDoctorAppointmentBackend.dto.MedicationDto;
import com.example.PatientDoctorAppointmentBackend.service.MedicationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("api/medication")
public class MedicationController {
    private MedicationService medicationService;
    @PreAuthorize("hasRole(\"DOCTOR\")")
    @PostMapping("add")
    public ResponseEntity<MedicationDto> addMedication( @RequestBody MedicationDto medicationDto) {
        MedicationDto savedMedication = medicationService.addMedication(medicationDto);
        return new ResponseEntity<>(savedMedication, HttpStatus.CREATED);
    }
}
