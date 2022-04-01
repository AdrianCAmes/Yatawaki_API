package com.orchestrator.orchestrator.model.dto.trivia.request;

import com.orchestrator.orchestrator.model.dto.game.request.GameOpenRequestDto;
import lombok.Data;

@Data
public class TriviaOpenRequestDto extends GameOpenRequestDto {
    private String hallCode;
    private Integer notesPool;
    private Long idUser;
}
