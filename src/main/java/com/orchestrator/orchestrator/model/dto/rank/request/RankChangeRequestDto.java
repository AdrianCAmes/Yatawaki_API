package com.orchestrator.orchestrator.model.dto.rank.request;

import lombok.Data;

@Data
public class RankChangeRequestDto {
    private Long idRank;
    private String name;
    private Integer level;
    private Integer maxExperience;
    private Integer status;
}
