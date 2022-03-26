package com.orchestrator.orchestrator.model.dto.hiddenrecord.request;

import com.orchestrator.orchestrator.model.dto.unlockable.request.UnlockableCreateRequestDto;
import lombok.Data;

@Data
public class HiddenRecordCreateRequestDto extends UnlockableCreateRequestDto {
    private String recordContent;
}
