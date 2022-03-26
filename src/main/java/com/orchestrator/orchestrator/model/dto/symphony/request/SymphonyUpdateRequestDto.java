package com.orchestrator.orchestrator.model.dto.symphony.request;

import com.orchestrator.orchestrator.model.dto.unlockable.request.UnlockableUpdateRequestDto;
import lombok.Data;

@Data
public class SymphonyUpdateRequestDto extends UnlockableUpdateRequestDto {
    private Long idComposer;
    private Integer year;
    private Integer duration;
    private String type;
}
