package com.orchestrator.orchestrator.model.dto.userunlockable.request;

import lombok.Data;

@Data
public class UserUnlockableCreateRequestDto {
    private Long idUser;
    private Long idUnlockable;
}
