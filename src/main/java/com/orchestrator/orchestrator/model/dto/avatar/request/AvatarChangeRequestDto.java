package com.orchestrator.orchestrator.model.dto.avatar.request;

import com.orchestrator.orchestrator.model.dto.unlockable.request.UnlockableChangeRequestDto;
import lombok.Data;

@Data
public class AvatarChangeRequestDto extends UnlockableChangeRequestDto {
    private String enhancedFeaturesJson;
}
