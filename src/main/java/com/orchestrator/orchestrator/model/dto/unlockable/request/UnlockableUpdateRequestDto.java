package com.orchestrator.orchestrator.model.dto.unlockable.request;

import lombok.Data;

@Data
public class UnlockableUpdateRequestDto {
    private Long idUnlockable;
    private String name;
    private String description;
    private String rareness;
    private String unlockerType;
    private Integer unlockerValue;
    private Integer currencyCost;
    private byte[] icon;
    private Integer status;
}
