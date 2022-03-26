package com.orchestrator.orchestrator.model.dto.avatar.request;

import com.orchestrator.orchestrator.model.dto.unlockable.request.UnlockableCreateRequestDto;
import lombok.Data;

@Data
public class AvatarCreateRequestDto extends UnlockableCreateRequestDto {
    private String enhancedFeaturesJson;
}
