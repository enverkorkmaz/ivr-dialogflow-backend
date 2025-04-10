package com.enver.ivr_ai.service.impl;

import com.enver.ivr_ai.dto.AppointmentRequestDto;
import com.enver.ivr_ai.dto.AppointmentResponseDto;
import com.enver.ivr_ai.metrics.MetricsService;
import com.enver.ivr_ai.model.Appointment;
import com.enver.ivr_ai.repository.AppointmentRepository;
import com.enver.ivr_ai.service.AppointmentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final MetricsService metricsService;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, MetricsService metricsService) {
        this.appointmentRepository = appointmentRepository;
        this.metricsService = metricsService;
    }

    @Transactional
    @Override
    public AppointmentResponseDto createAppointment(AppointmentRequestDto dto) {
        Appointment appointment = Appointment.builder()
                .userName(dto.getUserName())
                .appointmentDateTime(dto.getAppointmentDateTime())
                .createdAt(LocalDateTime.now())
                .build();

        Appointment saved = appointmentRepository.save(appointment);

        metricsService.incrementAppointmentCreated();
        return mapToResponseDto(saved);
    }

    @Override
    public List<AppointmentResponseDto> getAllAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentResponseDto getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .map(this::mapToResponseDto)
                .orElse(null);
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    private AppointmentResponseDto mapToResponseDto(Appointment appointment) {
        return AppointmentResponseDto.builder()
                .id(appointment.getId())
                .userName(appointment.getUserName())
                .appointmentDateTime(appointment.getAppointmentDateTime())
                .createdAt(appointment.getCreatedAt())
                .build();
    }
}