package com.example.PatientDoctorAppointmentBackend.service.Impl;

import com.example.PatientDoctorAppointmentBackend.dto.AppointmentDto;
import com.example.PatientDoctorAppointmentBackend.dto.DoctorRegisterDto;
import com.example.PatientDoctorAppointmentBackend.dto.PatientRegisterDto;
import com.example.PatientDoctorAppointmentBackend.entity.Appointment;
import com.example.PatientDoctorAppointmentBackend.entity.Doctor;
import com.example.PatientDoctorAppointmentBackend.exception.AppointmentException;
import com.example.PatientDoctorAppointmentBackend.repository.AppointmentRepository;
import com.example.PatientDoctorAppointmentBackend.repository.DoctorRepository;
import com.example.PatientDoctorAppointmentBackend.repository.PatientRepository;
import com.example.PatientDoctorAppointmentBackend.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private AppointmentRepository appointmentRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private ModelMapper modelMapper;
    @Override
    public AppointmentDto bookAppointment(AppointmentDto appointmentDto) throws AppointmentException{
          Long doctorId = (appointmentDto.getDoctorId());
        Long patientId = (appointmentDto.getPatientId());
//        Long doctorId = (appointmentDto.getDoctor()).getDoctorId();
//        Long patientId = (appointmentDto.getPatient()).getPatientId();
        LocalDateTime appointmentDateTime = appointmentDto.getAppointmentDateTime();
        //retrieve the Doctor entity for given doctorId and appointmentDateTime
        Optional<Appointment> appointments = appointmentRepository.findBydoctorIdAndAppointmentDateTime(doctorId,appointmentDateTime);
        if(appointments.isPresent()){
            appointmentDto.setAppointmentStatus("Slot not available");
            throw new AppointmentException("Doctor Appointment Slot not available");
        }else{
            appointmentDto.setAppointmentStatus("confirmed");
        }

        Appointment appointment = modelMapper.map(appointmentDto, Appointment.class);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        AppointmentDto appointmentDto1 =modelMapper.map(savedAppointment,AppointmentDto.class);
        appointmentDto1.setDoctor(modelMapper.map(doctorRepository.findById(doctorId), DoctorRegisterDto.class));
        appointmentDto1.setPatient(modelMapper.map(patientRepository.findById(patientId), PatientRegisterDto.class));
        return appointmentDto1;
    }
}
