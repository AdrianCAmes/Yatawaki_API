package com.orchestrator.orchestrator.model.dto.concert.request;

import com.orchestrator.orchestrator.model.dto.DtoTest;

public class ConcertUpdateRequestDtoTest extends DtoTest<ConcertUpdateRequestDto> {
    @Override
    protected ConcertUpdateRequestDto getInstance() {
        return new ConcertUpdateRequestDto();
    }
}