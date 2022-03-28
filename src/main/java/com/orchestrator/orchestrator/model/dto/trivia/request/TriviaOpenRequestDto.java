package com.orchestrator.orchestrator.model.dto.trivia.request;

import com.orchestrator.orchestrator.model.dto.game.request.GameCreateRequestDto;
import lombok.Data;

@Data
public class TriviaOpenRequestDto extends GameCreateRequestDto {
    private String hallCode;
    private Integer currencyPool;
    private Long idUser;
}
