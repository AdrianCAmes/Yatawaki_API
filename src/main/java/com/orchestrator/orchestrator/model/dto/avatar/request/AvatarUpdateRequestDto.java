package com.orchestrator.orchestrator.model.dto.avatar.request;

import com.orchestrator.orchestrator.model.dto.unlockable.request.UnlockableUpdateRequestDto;
import lombok.Data;

@Data
public class AvatarUpdateRequestDto extends UnlockableUpdateRequestDto {
    private String enhancedFeaturesJson;
}
