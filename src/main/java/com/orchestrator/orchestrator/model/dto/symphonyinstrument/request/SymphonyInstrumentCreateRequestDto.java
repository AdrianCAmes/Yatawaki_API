package com.orchestrator.orchestrator.model.dto.symphonyinstrument.request;

import lombok.Data;

@Data
public class SymphonyInstrumentCreateRequestDto {
    private Long idSymphony;
    private Long idInstrument;
    private String track;
    private String position;
}
