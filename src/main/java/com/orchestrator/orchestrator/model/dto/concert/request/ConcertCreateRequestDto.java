package com.orchestrator.orchestrator.model.dto.concert.request;

import com.orchestrator.orchestrator.model.dto.game.request.GameCreateRequestDto;
import lombok.Data;

@Data
public class ConcertCreateRequestDto extends GameCreateRequestDto {
    private Long idUser;
}
