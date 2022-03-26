package com.orchestrator.orchestrator.model.dto.userrank.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRankUpdateRequestDto {
    private Long idUserRank;
    private Long idUser;
    private Long idRank;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer currentExperience;
    private Integer status;
}
