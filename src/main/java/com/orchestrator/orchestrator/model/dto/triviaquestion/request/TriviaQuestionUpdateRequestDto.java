package com.orchestrator.orchestrator.model.dto.triviaquestion.request;

import lombok.Data;

@Data
public class TriviaQuestionUpdateRequestDto {
    private Long idTriviaQuestion;
    private Long idTrivia;
    private Long idQuestion;
    private Integer status;
}
