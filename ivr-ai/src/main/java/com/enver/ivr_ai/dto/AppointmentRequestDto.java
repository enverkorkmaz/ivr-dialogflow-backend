package com.enver.ivr_ai.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentRequestDto {
    @NotBlank(message = "User name is required")
    public String userName;
    @Future(message = "Appointment date and time must be in the future")
    private LocalDateTime appointmentDateTime;
}
