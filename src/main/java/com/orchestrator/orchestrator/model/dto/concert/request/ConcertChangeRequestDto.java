package com.orchestrator.orchestrator.model.dto.concert.request;

import com.orchestrator.orchestrator.model.dto.game.request.GameChangeRequestDto;
import lombok.Data;

@Data
public class ConcertChangeRequestDto extends GameChangeRequestDto {
    private Long idUser;
    private Integer points;
    private Double accuracyRate;
    private Integer gesturesCompleted;
}
