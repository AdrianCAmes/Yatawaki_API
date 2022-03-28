package com.orchestrator.orchestrator.model.dto.trivia.request;

import lombok.Data;

@Data
public class TriviaJoinRequestDto {
    private Long idTrivia;
    private Long idUser;
}
