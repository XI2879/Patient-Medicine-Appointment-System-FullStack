package com.example.PatientDoctorAppointmentBackend.security;


import com.example.PatientDoctorAppointmentBackend.entity.Admin;
import com.example.PatientDoctorAppointmentBackend.entity.Doctor;
import com.example.PatientDoctorAppointmentBackend.entity.Patient;
import com.example.PatientDoctorAppointmentBackend.exception.NewBadCredentialsException;
import com.example.PatientDoctorAppointmentBackend.repository.AdminRepository;
import com.example.PatientDoctorAppointmentBackend.repository.DoctorRepository;
import com.example.PatientDoctorAppointmentBackend.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomAuthenticationManager implements AuthenticationManager {
    private AdminRepository adminRepository;
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication)  {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        if(isValidUser(username,password)){
            return new UsernamePasswordAuthenticationToken(username,password);
        }  else{
            throw new NewBadCredentialsException("invalid credentials") {
            } ;

        }
    }


    private boolean isValidUser(String username,String password) {
        Admin admin = adminRepository.findByUsername(username).orElse(null);
        if (admin != null && admin.getUsername().equals(username) && passwordEncoder.matches(password, admin.getPassword())) {
            return true;
        }

        Patient patient = patientRepository.findByUsername(username).orElse(null);
        if (patient != null && patient.getUsername().equals(username) && passwordEncoder.matches(password, patient.getPassword())) {
            return true;
        }

        Doctor doctor = doctorRepository.findByUsername(username).orElse(null);
        return doctor != null && doctor.getUsername().equals(username) && passwordEncoder.matches(password, doctor.getPassword());
    }
}
