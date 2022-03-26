package com.orchestrator.orchestrator.model.dto.userstatistics.request;

import lombok.Data;

@Data
public class UserStatisticsUpdateRequestDto {
    private Long idUserStatistics;
    private Integer triviasPlayed;
    private Integer triviasWon;
    private Integer concertsOrchestrated;
    private Double orchestrationAccuracy;
    private Integer status;
}
