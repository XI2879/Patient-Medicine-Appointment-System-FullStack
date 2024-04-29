package com.example.PatientDoctorAppointmentBackend.service.Impl;

import com.example.PatientDoctorAppointmentBackend.dto.*;
import com.example.PatientDoctorAppointmentBackend.entity.Admin;
import com.example.PatientDoctorAppointmentBackend.entity.Doctor;
import com.example.PatientDoctorAppointmentBackend.entity.Patient;
import com.example.PatientDoctorAppointmentBackend.entity.Role;
import com.example.PatientDoctorAppointmentBackend.exception.APIException;
import com.example.PatientDoctorAppointmentBackend.repository.AdminRepository;
import com.example.PatientDoctorAppointmentBackend.repository.DoctorRepository;
import com.example.PatientDoctorAppointmentBackend.repository.PatientRepository;
import com.example.PatientDoctorAppointmentBackend.repository.RoleRepository;
import com.example.PatientDoctorAppointmentBackend.security.CustomAuthenticationManager;
import com.example.PatientDoctorAppointmentBackend.security.JwtUtil;
import com.example.PatientDoctorAppointmentBackend.service.AuthService;
import lombok.AllArgsConstructor;
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
public class AuthServiceImpl implements AuthService {
    private AdminRepository adminRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private CustomAuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private JwtUtil jwtUtil;

    @Override
    public String resisterAdmin(AdminRegisterDto adminRegisterDto) {
        if (adminRepository.existsByUsername(adminRegisterDto.getUsername())) {
            throw new APIException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
        // To check whether a doctor already exists with the same email
        if (adminRepository.existsByEmail(adminRegisterDto.getEmail())) {
            throw new APIException(HttpStatus.BAD_REQUEST, "Email already exists");
        }

        // Creating a doctor from the DTO and to store in the user table
        Admin admin = new Admin();
        admin.setName(adminRegisterDto.getName());
        admin.setEmail(adminRegisterDto.getEmail());
        admin.setUsername(adminRegisterDto.getUsername());
        admin.setPassword(passwordEncoder.encode(adminRegisterDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        roles.add(adminRole);

        admin.setRoles(roles);

        adminRepository.save(admin);
        return "Admin Registered Successfully!!";
    }
    @Override
    public JwtAuthResponse loginAdmin(AdminLoginDto adminLoginDto) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                adminLoginDto.getUsername(),
                adminLoginDto.getPassword()
        );
        authenticationManager.authenticate(token);
        String jwt = jwtUtil.generate(adminLoginDto.getUsername());

        Optional<Admin> adminOptional = adminRepository.findByUsername(adminLoginDto.getUsername());

        String role = "";
        if (adminOptional.isPresent()) {
            Admin loggedInAdmin = adminOptional.get();
            Optional<Role> optionalRole = loggedInAdmin.getRoles().stream().findFirst();

            Role adminRole = optionalRole.get();
            role = adminRole.getName();
        }

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(jwt);
        jwtAuthResponse.setRole(role);

        return jwtAuthResponse;
    }

    @Override
    public String registerDoctor(DoctorRegisterDto doctorRegisterDto) {
        if (doctorRepository.existsByUsername(doctorRegisterDto.getUsername())) {
            throw new APIException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
        // To check whether a doctor already exists with the same email
        if (doctorRepository.existsByEmail(doctorRegisterDto.getEmail())) {
            throw new APIException(HttpStatus.BAD_REQUEST, "Email already exists");
        }

        // Creating a doctor from the DTO and to store in the user table
        Doctor doctor = new Doctor();
        doctor.setName(doctorRegisterDto.getName());
        doctor.setEmail(doctorRegisterDto.getEmail());
        doctor.setUsername(doctorRegisterDto.getUsername());
        doctor.setSpecialization(doctorRegisterDto.getSpecialization());
        doctor.setPassword(passwordEncoder.encode(doctorRegisterDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_DOCTOR");
        roles.add(userRole);

        doctor.setRoles(roles);

        doctorRepository.save(doctor);
        return "Doctor Registered Successfully!!";
    }

    @Override
    public JwtAuthResponse loginDoctor(DoctorLoginDto doctorLoginDto) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                doctorLoginDto.getUsername(),
                doctorLoginDto.getPassword()
        );
        authenticationManager.authenticate(token);
        String jwt = jwtUtil.generate(doctorLoginDto.getUsername());

        Optional<Doctor> doctorOptional = doctorRepository.findByUsername(doctorLoginDto.getUsername());

        String role = "";
        if (doctorOptional.isPresent()) {
            Doctor loggedInDoctor = doctorOptional.get();
            Optional<Role> optionalRole = loggedInDoctor.getRoles().stream().findFirst();

            Role userRole = optionalRole.get();
            role = userRole.getName();
        }

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(jwt);
        jwtAuthResponse.setRole(role);

        return jwtAuthResponse;
    }
    @Override
    public String registerPatient(PatientRegisterDto patientRegisterDto) {

        if (patientRepository.existsByUsername(patientRegisterDto.getUsername())) {
            throw new APIException(HttpStatus.BAD_REQUEST, "Username already exists");
        }


        // Creating a patient from the DTO and to store in the user table
        Patient patient = new Patient();
        patient.setName(patientRegisterDto.getName());
        patient.setUsername(patientRegisterDto.getUsername());
        patient.setEmail(patientRegisterDto.getEmail());
        patient.setGender(patientRegisterDto.getGender());
        patient.setContactNumber(patientRegisterDto.getContactNumber());
        patient.setPassword(passwordEncoder.encode(patientRegisterDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_PATIENT");
        roles.add(userRole);

        patient.setRoles(roles);

        patientRepository.save(patient);
        return "Patient Registered Successfully!!";
    }

    @Override
    public JwtAuthResponse loginPatient(PatientLoginDto patientLoginDto) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                patientLoginDto.getUsername(),
                patientLoginDto.getPassword()
        );
        authenticationManager.authenticate(token);
        String jwt = jwtUtil.generate(patientLoginDto.getUsername());

        Optional<Patient> patientOptional = patientRepository.findByUsername(patientLoginDto.getUsername());

        String role = "";
        if (patientOptional.isPresent()) {
            Patient loggedInPatient = patientOptional.get();
            Optional<Role> optionalRole = loggedInPatient.getRoles().stream().findFirst();

            Role userRole = optionalRole.get();
            role = userRole.getName();
        }

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(jwt);
        jwtAuthResponse.setRole(role);

        return jwtAuthResponse;
    }




}
