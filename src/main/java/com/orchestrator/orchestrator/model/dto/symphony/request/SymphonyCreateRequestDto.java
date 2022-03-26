package com.orchestrator.orchestrator.model.dto.symphony.request;

import com.orchestrator.orchestrator.model.dto.unlockable.request.UnlockableCreateRequestDto;
import lombok.Data;

@Data
public class SymphonyCreateRequestDto extends UnlockableCreateRequestDto {
    private Long idComposer;
    private Integer year;
    private Integer duration;
    private String type;
}
