package com.orchestrator.orchestrator.model.dto.trivia.request;

import com.orchestrator.orchestrator.model.dto.game.request.GameCreateRequestDto;
import lombok.Data;

@Data
public class TriviaCreateRequestDto extends GameCreateRequestDto {
    private String hallId;
    private Integer currencyPool;
}
