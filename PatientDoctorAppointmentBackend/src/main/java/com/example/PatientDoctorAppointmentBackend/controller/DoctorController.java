package com.example.PatientDoctorAppointmentBackend.controller;

import com.example.PatientDoctorAppointmentBackend.dto.DoctorLoginDto;
import com.example.PatientDoctorAppointmentBackend.dto.DoctorRegisterDto;
import com.example.PatientDoctorAppointmentBackend.dto.JwtAuthResponse;
import com.example.PatientDoctorAppointmentBackend.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("api/doctor")
public class DoctorController {
    private DoctorService doctorService;


    @PreAuthorize("hasAnyRole(\"ADMIN\", \"PATIENT\", \"DOCTOR\")")
    @GetMapping("all")
    public ResponseEntity<List<DoctorRegisterDto>> getAllDoctors() {
        List<DoctorRegisterDto> doctorRegisterDtoList = doctorService.viewAllDoctors();
        return ResponseEntity.ok().body(doctorRegisterDtoList);
    }
}
