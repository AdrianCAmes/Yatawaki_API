package com.orchestrator.orchestrator.model.dto.userunlockable.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserUnlockableUpdateRequestDto {
    private Long idUserUnlockable;
    private Long idUser;
    private Long idUnlockable;
    private LocalDate unlockedDate;
    private Integer status;
}
