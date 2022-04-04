package com.orchestrator.orchestrator.model.dto.concert.request;

import lombok.Data;

@Data
public class ConcertCompleteRequestDto {
    private Long idConcert;
    private Integer gainedExperience;
    private Integer points;
    private Double accuracyRate;
    private Integer gesturesCompleted;
    private Integer gainedCoins;
}
