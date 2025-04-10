package com.enver.ivr_ai.controller;

import com.enver.ivr_ai.service.DialogflowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/webhook")
public class DialogflowWebhookController {

    private final DialogflowService dialogflowService;

    public DialogflowWebhookController(DialogflowService dialogflowService) {
        this.dialogflowService = dialogflowService;
    }

    @PostMapping("/dialogflow")
    public ResponseEntity<Map<String, Object>> handleDialogflow(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = dialogflowService.handleDialogflowRequest(request);
        return ResponseEntity.ok(response);
    }
}
