package com.example.PatientDoctorAppointmentBackend.service.Impl;

import com.example.PatientDoctorAppointmentBackend.dto.MedicationDto;
import com.example.PatientDoctorAppointmentBackend.dto.PatientRegisterDto;
import com.example.PatientDoctorAppointmentBackend.entity.Medication;
import com.example.PatientDoctorAppointmentBackend.entity.Patient;
import com.example.PatientDoctorAppointmentBackend.repository.MedicationRepository;
import com.example.PatientDoctorAppointmentBackend.repository.PatientRepository;
import com.example.PatientDoctorAppointmentBackend.service.MedicationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MedicationServiceImpl implements MedicationService {
    private MedicationRepository medicationRepository;
    private PatientRepository patientRepository;
    private ModelMapper modelMapper;
    @Override
    public MedicationDto addMedication(MedicationDto medicationDto) {
        Long patientId = medicationDto.getPatientId();
        Medication medication = modelMapper.map(medicationDto, Medication.class);
        // Retrieve patient information from repository using patientId
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));

        // Save medication
        Medication savedMedication = medicationRepository.save(medication);
        // Map saved medication to MedicationDto
        MedicationDto medicationDto1 = modelMapper.map(savedMedication, MedicationDto.class);
        // Map patient information to PatientRegisterDto and set it in medicationDto1
        medicationDto1.setPatient(modelMapper.map(patient, PatientRegisterDto.class));
        return medicationDto1;
    }
}
