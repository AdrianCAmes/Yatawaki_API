package com.orchestrator.orchestrator.model.dto.question.request;

import lombok.Data;

@Data
public class QuestionChangeRequestDto {
    private Long idQuestion;
    private Long idRank;
    private String optionsJson;
    private Integer status;
}
