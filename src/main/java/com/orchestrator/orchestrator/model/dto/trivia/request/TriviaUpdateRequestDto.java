package com.orchestrator.orchestrator.model.dto.trivia.request;

import com.orchestrator.orchestrator.model.dto.game.request.GameUpdateRequestDto;
import lombok.Data;

@Data
public class TriviaUpdateRequestDto extends GameUpdateRequestDto {
    private String hallId;
    private Integer numberOfParticipants;
    private Integer currencyPool;
}
