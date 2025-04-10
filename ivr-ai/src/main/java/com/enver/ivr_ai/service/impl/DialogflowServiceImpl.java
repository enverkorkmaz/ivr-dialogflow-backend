package com.enver.ivr_ai.service.impl;

import com.enver.ivr_ai.dto.AppointmentRequestDto;
import com.enver.ivr_ai.dto.AppointmentResponseDto;
import com.enver.ivr_ai.service.AppointmentService;
import com.enver.ivr_ai.service.DialogflowService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class DialogflowServiceImpl implements DialogflowService {

    private final AppointmentService appointmentService;

    public DialogflowServiceImpl(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Override
    public Map<String, Object> handleDialogflowRequest(Map<String, Object> request) {
        String userName = extractUserName(request);
        LocalDateTime appointmentDateTime = extractAppointmentDateTime(request);

        if (userName == null || appointmentDateTime == null) {
            return createErrorResponse("Tarih veya kullanıcı adı eksik. Lütfen tekrar deneyin.");
        }

        AppointmentRequestDto dto = new AppointmentRequestDto();
        dto.setUserName(userName);
        dto.setAppointmentDateTime(appointmentDateTime);

        AppointmentResponseDto saved = appointmentService.createAppointment(dto);

        return createSuccessResponse(userName, saved.getAppointmentDateTime());
    }

    @SuppressWarnings("unchecked")
    private String extractUserName(Map<String, Object> request) {
        try {
            Map<String, Object> queryResult = (Map<String, Object>) request.get("queryResult");
            Map<String, Object> parameters = (Map<String, Object>) queryResult.get("parameters");
            return (String) parameters.get("userName");
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private LocalDateTime extractAppointmentDateTime(Map<String, Object> request) {
        try {
            Map<String, Object> queryResult = (Map<String, Object>) request.get("queryResult");
            Map<String, Object> parameters = (Map<String, Object>) queryResult.get("parameters");
            Object appointmentDateTimeObj = parameters.get("appointmentDateTime");

            String appointmentDateTimeStr = null;

            if (appointmentDateTimeObj instanceof Map<?, ?> map && map.containsKey("date_time")) {
                appointmentDateTimeStr = (String) map.get("date_time");
            } else if (appointmentDateTimeObj instanceof String s) {
                appointmentDateTimeStr = s;
            }

            return appointmentDateTimeStr != null
                    ? OffsetDateTime.parse(appointmentDateTimeStr).toLocalDateTime()
                    : null;
        } catch (Exception e) {
            return null;
        }
    }

    private Map<String, Object> createErrorResponse(String message) {
        Map<String, Object> error = new HashMap<>();
        error.put("fulfillmentText", message);
        return error;
    }

    private Map<String, Object> createSuccessResponse(String userName, LocalDateTime appointmentDateTime) {
        Map<String, Object> response = new HashMap<>();
        response.put("fulfillmentText", userName + " için " + appointmentDateTime + " tarihinde randevu oluşturuldu!");
        return response;
    }


}
