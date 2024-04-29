package com.example.PatientDoctorAppointmentBackend.security;


import com.example.PatientDoctorAppointmentBackend.entity.Admin;
import com.example.PatientDoctorAppointmentBackend.entity.Doctor;
import com.example.PatientDoctorAppointmentBackend.entity.Patient;
import com.example.PatientDoctorAppointmentBackend.repository.AdminRepository;
import com.example.PatientDoctorAppointmentBackend.repository.DoctorRepository;
import com.example.PatientDoctorAppointmentBackend.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private AdminRepository adminRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail).get();

        if (admin != null) {
            Set<GrantedAuthority> authorities = admin.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toSet());

            return new org.springframework.security.core.userdetails.User(
                    usernameOrEmail,
                    admin.getPassword(),
                    authorities
            );
        }

        Doctor doctor = doctorRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail).get();

        if(doctor != null){
            Set<GrantedAuthority> authorities = doctor.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toSet());

            return new org.springframework.security.core.userdetails.User(
                    usernameOrEmail,
                    doctor.getPassword(),
                    authorities
            );
        }

        Patient patient = patientRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail).get();

        Set<GrantedAuthority> authorities = patient.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                usernameOrEmail,
                patient.getPassword(),
                authorities
        );
    }
}
