package com.orchestrator.orchestrator.model.dto.trivia.request;

import com.orchestrator.orchestrator.model.dto.game.request.GameChangeRequestDto;
import lombok.Data;

@Data
public class TriviaChangeRequestDto extends GameChangeRequestDto {
    private String hallCode;
    private Integer numberOfParticipants;
    private Integer notesPool;
}
