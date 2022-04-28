package com.orchestrator.orchestrator.model.dto.unlockable.request;

import lombok.Data;

@Data
public class UnlockableCreateRequestDto {
    private String name;
    private String description;
    private String rareness;
    private String unlockerType;
    private Integer unlockerValue;
    private String icon;
    private Integer coinsCost;
}
