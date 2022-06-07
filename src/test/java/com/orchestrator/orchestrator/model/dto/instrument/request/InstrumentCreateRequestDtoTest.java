package com.orchestrator.orchestrator.model.dto.instrument.request;

import com.orchestrator.orchestrator.model.dto.DtoTest;

public class InstrumentCreateRequestDtoTest extends DtoTest<InstrumentCreateRequestDto> {
    @Override
    protected InstrumentCreateRequestDto getInstance() {
        return new InstrumentCreateRequestDto();
    }
}