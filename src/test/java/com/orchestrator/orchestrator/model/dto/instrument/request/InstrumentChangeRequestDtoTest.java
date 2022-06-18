package com.orchestrator.orchestrator.model.dto.instrument.request;

import com.orchestrator.orchestrator.model.dto.DtoTest;

public class InstrumentChangeRequestDtoTest extends DtoTest<InstrumentChangeRequestDto> {
    @Override
    protected InstrumentChangeRequestDto getInstance() {
        return new InstrumentChangeRequestDto();
    }
}
