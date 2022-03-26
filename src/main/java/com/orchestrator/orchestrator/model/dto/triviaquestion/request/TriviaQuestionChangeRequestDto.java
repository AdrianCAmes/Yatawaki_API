package com.orchestrator.orchestrator.model.dto.triviaquestion.request;

import lombok.Data;

@Data
public class TriviaQuestionChangeRequestDto {
    private Long idTriviaQuestion;
    private Long idTrivia;
    private Long idQuestion;
    private Integer status;
}
