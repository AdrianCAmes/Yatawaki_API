package com.orchestrator.orchestrator.model.dto.userunlockable.request;

import lombok.Data;

@Data
public class UserUnlockableUnlockRequestDto {
    private Long idUser;
    private String unlockerType;
    private Integer unlockerValue;
}
