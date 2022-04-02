package com.orchestrator.orchestrator.model.dto.user.response;

import lombok.Data;

@Data
public class UserResumeResponseDto {
    private Long id;
    private Integer coinsOwned;
    private Integer notesOwned;
    private Integer currentExperience;
    private Integer level;
    private byte[] icon;
}
