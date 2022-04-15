package com.orchestrator.orchestrator.model.dto.userunlockable.request;

import lombok.Data;

@Data
public class UserUnlockableTradeRequestDto {
    private Long idUser;
    private Long idUnlockable;
}
