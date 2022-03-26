package com.orchestrator.orchestrator.model.dto.game.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GameUpdateRequestDto {
    private Long idGame;
    private Long idSymphony;
    private LocalDate playedDate;
    private Integer status;
}
