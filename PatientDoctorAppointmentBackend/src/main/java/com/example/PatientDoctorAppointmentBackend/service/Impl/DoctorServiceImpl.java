package com.example.PatientDoctorAppointmentBackend.service.Impl;

import com.example.PatientDoctorAppointmentBackend.dto.DoctorLoginDto;
import com.example.PatientDoctorAppointmentBackend.dto.DoctorRegisterDto;
import com.example.PatientDoctorAppointmentBackend.dto.JwtAuthResponse;
import com.example.PatientDoctorAppointmentBackend.entity.Doctor;
import com.example.PatientDoctorAppointmentBackend.entity.Role;
import com.example.PatientDoctorAppointmentBackend.exception.APIException;
import com.example.PatientDoctorAppointmentBackend.repository.DoctorRepository;
import com.example.PatientDoctorAppointmentBackend.repository.RoleRepository;
import com.example.PatientDoctorAppointmentBackend.security.CustomAuthenticationManager;
import com.example.PatientDoctorAppointmentBackend.security.JwtUtil;
import com.example.PatientDoctorAppointmentBackend.service.DoctorService;
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
public class DoctorServiceImpl implements DoctorService {
    private DoctorRepository doctorRepository;
    private ModelMapper modelMapper;
    @Override
    public List<DoctorRegisterDto> viewAllDoctors() {
        List<Doctor> doctors=doctorRepository.findAll();

        return doctors.stream().map(doctor -> modelMapper.map(doctor, DoctorRegisterDto.class))
                .collect(Collectors.toList());
    }
}
