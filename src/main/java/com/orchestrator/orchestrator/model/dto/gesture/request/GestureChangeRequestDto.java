package com.orchestrator.orchestrator.model.dto.gesture.request;

import lombok.Data;

@Data
public class GestureChangeRequestDto {
    private Long idGesture;
    private String name;
    private String description;
    private String status;
}
