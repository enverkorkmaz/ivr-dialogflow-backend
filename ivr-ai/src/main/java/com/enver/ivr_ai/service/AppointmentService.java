package com.enver.ivr_ai.service;

import com.enver.ivr_ai.dto.AppointmentRequestDto;
import com.enver.ivr_ai.dto.AppointmentResponseDto;

import java.util.List;

public interface AppointmentService {
    AppointmentResponseDto createAppointment(AppointmentRequestDto dto);
    List<AppointmentResponseDto> getAllAppointments();
    AppointmentResponseDto getAppointmentById(Long id);
    void deleteAppointment(Long id);
}