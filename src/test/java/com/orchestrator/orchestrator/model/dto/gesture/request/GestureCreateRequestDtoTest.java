package com.orchestrator.orchestrator.model.dto.gesture.request;

import com.orchestrator.orchestrator.model.dto.DtoTest;

public class GestureCreateRequestDtoTest extends DtoTest<GestureCreateRequestDto> {
    @Override
    protected GestureCreateRequestDto getInstance() {
        return new GestureCreateRequestDto();
    }
}