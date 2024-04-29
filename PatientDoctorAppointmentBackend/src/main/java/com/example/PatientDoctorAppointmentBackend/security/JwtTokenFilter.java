package com.example.PatientDoctorAppointmentBackend.security;


import com.example.PatientDoctorAppointmentBackend.entity.Admin;
import com.example.PatientDoctorAppointmentBackend.entity.Doctor;
import com.example.PatientDoctorAppointmentBackend.entity.Patient;
import com.example.PatientDoctorAppointmentBackend.entity.Role;
import com.example.PatientDoctorAppointmentBackend.repository.AdminRepository;
import com.example.PatientDoctorAppointmentBackend.repository.DoctorRepository;
import com.example.PatientDoctorAppointmentBackend.repository.PatientRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class JwtTokenFilter extends OncePerRequestFilter {
    private JwtUtil jwtUtil;
    private AdminRepository adminRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader ==  null || !authorizationHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }
        String token = authorizationHeader.split(" ")[1];
        if(!jwtUtil.validate(token)){
            filterChain.doFilter(request,response);
            return;
        }
        String username = jwtUtil.getUsername(token);
        Admin admin = adminRepository.findByUsername(username).orElse(null);
        Doctor doctor = doctorRepository.findByUsername(username).orElse(null);
        Patient patient = patientRepository.findByUsername(username).orElse(null);

        if (admin != null) {
            authenticateUser(username, admin.getPassword(), admin.getRoles(), request);
        } else if (doctor != null) {
            authenticateUser(username, doctor.getPassword(), doctor.getRoles(), request);
        } else if (patient != null) {
            authenticateUser(username, patient.getPassword(), patient.getRoles(), request);
        }
        filterChain.doFilter(request, response);
    }
    private void authenticateUser(String username, String password, Set<Role> roles, HttpServletRequest request) {
        Set<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password, authorities);
        authenticationToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
