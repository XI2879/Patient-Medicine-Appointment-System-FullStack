package com.example.PatientDoctorAppointmentBackend.entity;

import com.example.PatientDoctorAppointmentBackend.dto.PatientRegisterDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "medicationDetails")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String medication;
    private String dosage;
    private Integer days;
    @ManyToOne
    @JoinColumn(name = "pateint_id")
    private Patient patient;

}
