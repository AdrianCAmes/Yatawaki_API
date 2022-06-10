package com.orchestrator.orchestrator.model.dto.concert.request;

import com.orchestrator.orchestrator.model.dto.DtoTest;
import com.orchestrator.orchestrator.model.dto.concert.request.ConcertChangeRequestDto;


public class ConcertChangeRequestDtoTest extends DtoTest<ConcertChangeRequestDto> {

    @Override
    protected ConcertChangeRequestDto getInstance() {
        return new ConcertChangeRequestDto();
    }


}
