package com.orchestrator.orchestrator.model.dto.triviauser.request;

import lombok.Data;

@Data
public class TriviaUserChangeRequestDto {
    private Long idTriviaUser;
    private Long idTrivia;
    private Long idUser;
    private Boolean isWinner;
    private String answeredQuestionsJson;
    private Integer points;
    private Double accuracyRate;
    private Integer status;
}
