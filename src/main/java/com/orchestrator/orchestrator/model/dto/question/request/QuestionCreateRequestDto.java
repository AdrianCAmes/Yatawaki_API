package com.orchestrator.orchestrator.model.dto.question.request;

import lombok.Data;

@Data
public class QuestionCreateRequestDto {
    private Long idRank;
    private String description;
    private String optionsJson;
}
