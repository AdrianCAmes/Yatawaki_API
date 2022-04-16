package com.orchestrator.orchestrator.utils.impl;

import com.orchestrator.orchestrator.model.Instrument;
import com.orchestrator.orchestrator.model.Symphony;
import com.orchestrator.orchestrator.model.SymphonyInstrument;
import com.orchestrator.orchestrator.model.dto.symphonyinstrument.request.SymphonyInstrumentChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.symphonyinstrument.request.SymphonyInstrumentCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.symphonyinstrument.request.SymphonyInstrumentUpdateRequestDto;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.SymphonyInstrumentUtils;
import com.orchestrator.orchestrator.utils.constants.SymphonyInstrumentStatusConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SymphonyInstrumentUtilsImpl implements SymphonyInstrumentUtils {
    private final GeneralUtils generalUtils;

    @Override
    public SymphonyInstrument buildDomainFromCreateRequestDto(SymphonyInstrumentCreateRequestDto symphonyInstrumentCreateRequestDto) throws IllegalAccessException {
        Symphony symphony = new Symphony();
        symphony.setIdUnlockable(symphonyInstrumentCreateRequestDto.getIdSymphony());
        Instrument instrument = new Instrument();
        instrument.setIdInstrument(symphonyInstrumentCreateRequestDto.getIdInstrument());
        SymphonyInstrument symphonyInstrument = new SymphonyInstrument();
        symphonyInstrument.setSymphony(symphony);
        symphonyInstrument.setInstrument(instrument);
        symphonyInstrument.setStatus(SymphonyInstrumentStatusConstants.ACTIVE.getValue());
        generalUtils.mapFields(symphonyInstrumentCreateRequestDto, symphonyInstrument);
        return symphonyInstrument;
    }

    @Override
    public SymphonyInstrument buildDomainFromUpdateRequestDto(SymphonyInstrumentUpdateRequestDto symphonyInstrumentUpdateRequestDto) throws IllegalAccessException {
        SymphonyInstrument symphonyInstrument = new SymphonyInstrument();
        if (symphonyInstrumentUpdateRequestDto.getIdSymphony() != null) {
            Symphony symphony = new Symphony();
            symphony.setIdUnlockable(symphonyInstrumentUpdateRequestDto.getIdSymphony());
            symphonyInstrument.setSymphony(symphony);
        }
        if (symphonyInstrumentUpdateRequestDto.getIdInstrument() != null) {
            Instrument instrument = new Instrument();
            instrument.setIdInstrument(symphonyInstrumentUpdateRequestDto.getIdInstrument());
            symphonyInstrument.setInstrument(instrument);
        }
        generalUtils.mapFields(symphonyInstrumentUpdateRequestDto, symphonyInstrument);
        return symphonyInstrument;
    }

    @Override
    public SymphonyInstrument buildDomainFromChangeRequestDto(SymphonyInstrumentChangeRequestDto symphonyInstrumentChangeRequestDto) throws IllegalAccessException {
        Symphony symphony = new Symphony();
        symphony.setIdUnlockable(symphonyInstrumentChangeRequestDto.getIdSymphony());
        Instrument instrument = new Instrument();
        instrument.setIdInstrument(symphonyInstrumentChangeRequestDto.getIdInstrument());
        SymphonyInstrument symphonyInstrument = new SymphonyInstrument();
        symphonyInstrument.setSymphony(symphony);
        symphonyInstrument.setInstrument(instrument);
        generalUtils.mapFields(symphonyInstrumentChangeRequestDto, symphonyInstrument);
        return symphonyInstrument;
    }

    @Override
    public SymphonyInstrumentCreateRequestDto buildCreateRequestDtoFromDomain(SymphonyInstrument symphonyInstrument) throws IllegalAccessException {
        SymphonyInstrumentCreateRequestDto symphonyInstrumentCreateRequestDto = new SymphonyInstrumentCreateRequestDto();
        symphonyInstrumentCreateRequestDto.setIdSymphony(symphonyInstrument.getSymphony().getIdUnlockable());
        symphonyInstrumentCreateRequestDto.setIdInstrument(symphonyInstrument.getInstrument().getIdInstrument());
        generalUtils.mapFields(symphonyInstrument, symphonyInstrumentCreateRequestDto);
        return symphonyInstrumentCreateRequestDto;
    }

    @Override
    public SymphonyInstrumentUpdateRequestDto buildUpdateRequestDtoFromDomain(SymphonyInstrument symphonyInstrument) throws IllegalAccessException {
        SymphonyInstrumentUpdateRequestDto symphonyInstrumentUpdateRequestDto = new SymphonyInstrumentUpdateRequestDto();
        symphonyInstrumentUpdateRequestDto.setIdSymphony(symphonyInstrument.getSymphony().getIdUnlockable());
        symphonyInstrumentUpdateRequestDto.setIdInstrument(symphonyInstrument.getInstrument().getIdInstrument());
        generalUtils.mapFields(symphonyInstrument, symphonyInstrumentUpdateRequestDto);
        return symphonyInstrumentUpdateRequestDto;
    }

    @Override
    public SymphonyInstrumentChangeRequestDto buildChangeRequestDtoFromDomain(SymphonyInstrument symphonyInstrument) throws IllegalAccessException {
        SymphonyInstrumentChangeRequestDto symphonyInstrumentChangeRequestDto = new SymphonyInstrumentChangeRequestDto();
        symphonyInstrumentChangeRequestDto.setIdSymphony(symphonyInstrument.getSymphony().getIdUnlockable());
        symphonyInstrumentChangeRequestDto.setIdInstrument(symphonyInstrument.getInstrument().getIdInstrument());
        generalUtils.mapFields(symphonyInstrument, symphonyInstrumentChangeRequestDto);
        return symphonyInstrumentChangeRequestDto;
    }
}
