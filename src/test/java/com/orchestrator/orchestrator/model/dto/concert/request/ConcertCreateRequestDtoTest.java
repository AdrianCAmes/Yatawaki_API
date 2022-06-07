package com.orchestrator.orchestrator.model.dto.concert.request;

import com.orchestrator.orchestrator.model.dto.DtoTest;

public class ConcertCreateRequestDtoTest extends DtoTest<ConcertCreateRequestDto> {
    @Override
    protected ConcertCreateRequestDto getInstance() {
        return new ConcertCreateRequestDto();
    }
}