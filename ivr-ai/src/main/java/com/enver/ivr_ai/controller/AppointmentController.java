package com.enver.ivr_ai.controller;

import com.enver.ivr_ai.dto.AppointmentRequestDto;
import com.enver.ivr_ai.dto.AppointmentResponseDto;
import com.enver.ivr_ai.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<AppointmentResponseDto> createAppointment(@RequestBody @Valid AppointmentRequestDto requestDto) {
        AppointmentResponseDto appointmentResponseDto = appointmentService.createAppointment(requestDto);
        return ResponseEntity.ok(appointmentResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDto> getAppointmentById(@PathVariable Long id) {
        AppointmentResponseDto appointmentResponseDto = appointmentService.getAppointmentById(id);
        return appointmentResponseDto != null
                ? ResponseEntity.ok(appointmentResponseDto)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
