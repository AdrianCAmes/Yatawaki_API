package com.orchestrator.orchestrator.model.dto.userunlockable.response;

import com.orchestrator.orchestrator.model.Unlockable;
import lombok.Data;

import java.util.List;

@Data
public class UserUnlockableFilteredResponseDto {
    private List<Unlockable> achievements;
    private List<Unlockable> symphonies;
    private List<Unlockable> avatars;
}
