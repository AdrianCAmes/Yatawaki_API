package com.orchestrator.orchestrator.model.dto.instrument.request;

import lombok.Data;

@Data
public class InstrumentCreateRequestDto {
    private String name;
    private String description;
    private String type;
}