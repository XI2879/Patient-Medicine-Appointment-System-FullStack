package com.example.PatientDoctorAppointmentBackend.service.Impl;

import com.example.PatientDoctorAppointmentBackend.dto.JwtAuthResponse;
import com.example.PatientDoctorAppointmentBackend.dto.PatientLoginDto;
import com.example.PatientDoctorAppointmentBackend.dto.PatientRegisterDto;
import com.example.PatientDoctorAppointmentBackend.entity.Doctor;
import com.example.PatientDoctorAppointmentBackend.entity.Patient;
import com.example.PatientDoctorAppointmentBackend.entity.Role;
import com.example.PatientDoctorAppointmentBackend.exception.APIException;
import com.example.PatientDoctorAppointmentBackend.repository.PatientRepository;
import com.example.PatientDoctorAppointmentBackend.repository.RoleRepository;
import com.example.PatientDoctorAppointmentBackend.security.CustomAuthenticationManager;
import com.example.PatientDoctorAppointmentBackend.security.JwtUtil;
import com.example.PatientDoctorAppointmentBackend.service.PatientService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {
    private PatientRepository patientRepository;
    private ModelMapper modelMapper;
    @Override
    public List<PatientRegisterDto> viewAllPatients() {
        List<Patient> patientList = patientRepository.findAll();


        return patientList.stream().map(patient -> modelMapper.map(patient,PatientRegisterDto.class))
                .collect(Collectors.toList());
    }
}
