package com.orchestrator.orchestrator.model.dto.hiddenrecord.request;

import com.orchestrator.orchestrator.model.dto.unlockable.request.UnlockableUpdateRequestDto;
import lombok.Data;

@Data
public class HiddenRecordUpdateRequestDto extends UnlockableUpdateRequestDto {
    private String recordContent;
}
