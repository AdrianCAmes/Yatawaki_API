package com.orchestrator.orchestrator.model.dto.instrument.request;

import lombok.Data;

@Data
public class InstrumentChangeRequestDto {
    private Long idInstrument;
    private String name;
    private String description;
    private String type;
    private Integer status;
}
