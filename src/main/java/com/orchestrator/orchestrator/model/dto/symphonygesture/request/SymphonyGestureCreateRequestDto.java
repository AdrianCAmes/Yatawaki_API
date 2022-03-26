package com.orchestrator.orchestrator.model.dto.symphonygesture.request;

import lombok.Data;

@Data
public class SymphonyGestureCreateRequestDto {
    private Long idGesture;
    private Long idSymphony;
    private Integer beginningTime;
    private Integer endingTime;
}
