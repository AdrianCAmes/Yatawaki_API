package com.orchestrator.orchestrator.model.dto.symphonygesture.request;

import lombok.Data;

@Data
public class SymphonyGestureChangeRequestDto {
    private Long idSymphonyGesture;
    private Long idGesture;
    private Long idSymphony;
    private Integer beginningTime;
    private Integer endingTime;
    private Integer status;
}
