package com.enver.ivr_ai.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class MetricsService {

    private final Counter appointmentCreatedCounter;

    public MetricsService(MeterRegistry meterRegistry) {
        this.appointmentCreatedCounter = Counter
                .builder("ivr_appointments_created_total")
                .description("Toplam oluşturulan randevu sayısı")
                .register(meterRegistry);
    }

    public void incrementAppointmentCreated() {
        appointmentCreatedCounter.increment();
    }
}
