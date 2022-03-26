package com.orchestrator.orchestrator.utils;

import com.orchestrator.orchestrator.model.Instrument;
import com.orchestrator.orchestrator.model.dto.instrument.request.InstrumentChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.instrument.request.InstrumentCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.instrument.request.InstrumentUpdateRequestDto;

public interface InstrumentUtils {
    Instrument buildDomainFromCreateRequestDto(InstrumentCreateRequestDto instrumentCreateRequestDto) throws IllegalAccessException;
    Instrument buildDomainFromUpdateRequestDto(InstrumentUpdateRequestDto instrumentUpdateRequestDto) throws IllegalAccessException;
    Instrument buildDomainFromChangeRequestDto(InstrumentChangeRequestDto instrumentChangeRequestDto) throws IllegalAccessException;
    InstrumentCreateRequestDto buildCreateRequestDtoFromDomain(Instrument instrument) throws IllegalAccessException;
    InstrumentUpdateRequestDto buildUpdateRequestDtoFromDomain(Instrument instrument) throws IllegalAccessException;
    InstrumentChangeRequestDto buildChangeRequestDtoFromDomain(Instrument instrument) throws IllegalAccessException;
}
