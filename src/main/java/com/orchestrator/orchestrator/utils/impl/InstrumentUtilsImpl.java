package com.orchestrator.orchestrator.utils.impl;

import com.orchestrator.orchestrator.model.Instrument;
import com.orchestrator.orchestrator.model.dto.instrument.request.InstrumentChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.instrument.request.InstrumentCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.instrument.request.InstrumentUpdateRequestDto;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.InstrumentUtils;
import com.orchestrator.orchestrator.utils.constants.InstrumentStatusConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstrumentUtilsImpl implements InstrumentUtils {
    private final GeneralUtils generalUtils;

    @Override
    public Instrument buildDomainFromCreateRequestDto(InstrumentCreateRequestDto instrumentCreateRequestDto) throws IllegalAccessException {
        Instrument instrument = new Instrument();
        instrument.setStatus(InstrumentStatusConstants.ACTIVE.getValue());
        generalUtils.mapFields(instrumentCreateRequestDto, instrument);
        return instrument;
    }

    @Override
    public Instrument buildDomainFromUpdateRequestDto(InstrumentUpdateRequestDto instrumentUpdateRequestDto) throws IllegalAccessException {
        Instrument instrument = new Instrument();
        generalUtils.mapFields(instrumentUpdateRequestDto, instrument);
        return instrument;
    }

    @Override
    public Instrument buildDomainFromChangeRequestDto(InstrumentChangeRequestDto instrumentChangeRequestDto) throws IllegalAccessException {
        Instrument instrument = new Instrument();
        generalUtils.mapFields(instrumentChangeRequestDto, instrument);
        return instrument;
    }

    @Override
    public InstrumentCreateRequestDto buildCreateRequestDtoFromDomain(Instrument instrument) throws IllegalAccessException {
        InstrumentCreateRequestDto instrumentCreateRequestDto = new InstrumentCreateRequestDto();
        generalUtils.mapFields(instrument, instrumentCreateRequestDto);
        return instrumentCreateRequestDto;
    }

    @Override
    public InstrumentUpdateRequestDto buildUpdateRequestDtoFromDomain(Instrument instrument) throws IllegalAccessException {
        InstrumentUpdateRequestDto instrumentUpdateRequestDto = new InstrumentUpdateRequestDto();
        generalUtils.mapFields(instrument, instrumentUpdateRequestDto);
        return instrumentUpdateRequestDto;
    }

    @Override
    public InstrumentChangeRequestDto buildChangeRequestDtoFromDomain(Instrument instrument) throws IllegalAccessException {
        InstrumentChangeRequestDto instrumentChangeRequestDto = new InstrumentChangeRequestDto();
        generalUtils.mapFields(instrument, instrumentChangeRequestDto);
        return instrumentChangeRequestDto;
    }
}
