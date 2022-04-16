package com.orchestrator.orchestrator.model.dto.user.response;

import com.orchestrator.orchestrator.model.Unlockable;
import lombok.Data;

import java.util.List;

@Data
public class UserResumeResponseDto {
    private Long idUser;
    private Integer coinsOwned;
    private Integer currentExperience;
    private Integer level;
    private byte[] icon;
    private List<Unlockable> symphonies;
    private Boolean showTutorials;
}
