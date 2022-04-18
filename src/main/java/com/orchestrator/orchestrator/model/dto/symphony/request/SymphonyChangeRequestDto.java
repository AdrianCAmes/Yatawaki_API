package com.orchestrator.orchestrator.model.dto.symphony.request;

import com.orchestrator.orchestrator.model.dto.unlockable.request.UnlockableChangeRequestDto;
import lombok.Data;

@Data
public class SymphonyChangeRequestDto extends UnlockableChangeRequestDto {
    private Long idComposer;
    private Integer year;
    private Integer duration;
    private String type;
    private String previewTrack;
}
