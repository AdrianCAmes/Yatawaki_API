package com.orchestrator.orchestrator.model.dto.concert.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ConcertChangeRequestDto {
    private Long idConcert;
    private Long idSymphony;
    private LocalDate playedDate;
    private Integer status;
    private Long idUser;
    private Integer points;
    private Double accuracyRate;
    private Integer gesturesCompleted;
}
