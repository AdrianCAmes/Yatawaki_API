package com.orchestrator.orchestrator.model.dto.symphonyinstrument.request;

import lombok.Data;

@Data
public class SymphonyInstrumentChangeRequestDto {
    private Long idSymphonyInstrument;
    private Long idSymphony;
    private Long idInstrument;
    private Integer status;
}
