package com.orchestrator.orchestrator.model.dto.userrank.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRankUpgradeRequestDto {
    private Long idUser;
    private Integer gainedExperience;
}
