package com.orchestrator.orchestrator.model.dto.concert.request;

import com.orchestrator.orchestrator.model.dto.game.request.GameUpdateRequestDto;
import lombok.Data;

@Data
public class ConcertUpdateRequestDto extends GameUpdateRequestDto {
    private Long idUser;
    private Integer points;
    private Double accuracyRate;
    private Integer gesturesCompleted;
}
