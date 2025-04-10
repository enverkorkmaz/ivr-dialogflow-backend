package com.enver.ivr_ai.service;

import java.util.Map;

public interface DialogflowService {
    Map<String, Object> handleDialogflowRequest(Map<String, Object> request);
}
