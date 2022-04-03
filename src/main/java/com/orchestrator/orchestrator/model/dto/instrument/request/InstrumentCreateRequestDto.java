package com.orchestrator.orchestrator.model.dto.instrument.request;

import lombok.Data;

@Data
public class InstrumentCreateRequestDto {
    private String name;
    private String longDescription;
    private String shortDescription;
    private String type;
}
