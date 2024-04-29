package com.example.PatientDoctorAppointmentBackend.controller;

import com.example.PatientDoctorAppointmentBackend.dto.*;
import com.example.PatientDoctorAppointmentBackend.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*")
@RestController
@AllArgsConstructor
@RequestMapping("api/auth")
public class AuthController {
    private AuthService authService;
    @PostMapping("admin-login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody AdminLoginDto adminLoginDto) {
        JwtAuthResponse jwtAuthResponse = authService.loginAdmin(adminLoginDto);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping("admin-register")
    public ResponseEntity<String> register(@Valid @RequestBody AdminRegisterDto adminRegisterDto){
        String response = authService.resisterAdmin(adminRegisterDto);
        return ResponseEntity.ok(response);
    }
    @PostMapping("doctor-register")
    public ResponseEntity<String> registerDoctor(@Valid @RequestBody DoctorRegisterDto doctorRegisterDto) {
        String response = authService.registerDoctor(doctorRegisterDto);
        return ResponseEntity.ok(response);
    }
    @PostMapping("doctor-login")
    public ResponseEntity<JwtAuthResponse> loginDoctor(@RequestBody DoctorLoginDto doctorLoginDto) {
        JwtAuthResponse jwtAuthResponse = authService.loginDoctor(doctorLoginDto);
        return ResponseEntity.ok(jwtAuthResponse);
    }
    @PostMapping("patient-register")
    public ResponseEntity<String> register(@Valid @RequestBody PatientRegisterDto patientRegisterDto) {
        String response = authService.registerPatient(patientRegisterDto);
        return ResponseEntity.ok(response);
    }
    @PostMapping("patient-login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody PatientLoginDto patientLoginDto) {
        JwtAuthResponse jwtAuthResponse = authService.loginPatient(patientLoginDto);
        return ResponseEntity.ok(jwtAuthResponse);
    }

}
