package com.example.PatientDoctorAppointmentBackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doctorDetails")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;
    private String name;
    private String username;
    private String email;
    private String password;
    private String specialization;
    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
    private List<Appointment> appointments;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "doctor_roles",
            joinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "doctorId"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

}
