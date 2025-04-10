package com.enver.ivr_ai.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppLogger {
    @PostConstruct
    public void init() {
        log.info("LOG LOG LOG ENVER IVR AI");
    }
}
