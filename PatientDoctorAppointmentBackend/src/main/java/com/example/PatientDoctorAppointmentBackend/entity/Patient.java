package com.example.PatientDoctorAppointmentBackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patientDetails")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
    private String name;
    private String username;
    private String password;
    private String email;
    private String gender;
    private String contactNumber;
    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    private List<Appointment> appointments;
    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    private List<Medication> medications;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "patient_roles",
            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "patientId"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

}
