package com.orchestrator.orchestrator.model.dto.hiddenrecord.request;

import com.orchestrator.orchestrator.model.dto.unlockable.request.UnlockableChangeRequestDto;
import lombok.Data;

@Data
public class HiddenRecordChangeRequestDto extends UnlockableChangeRequestDto {
    private String recordContent;
}
