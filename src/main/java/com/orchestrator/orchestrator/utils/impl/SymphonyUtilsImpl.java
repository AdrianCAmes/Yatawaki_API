package com.orchestrator.orchestrator.utils.impl;

import com.orchestrator.orchestrator.model.Composer;
import com.orchestrator.orchestrator.model.Symphony;
import com.orchestrator.orchestrator.model.dto.symphony.request.SymphonyChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.symphony.request.SymphonyCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.symphony.request.SymphonyUpdateRequestDto;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.SymphonyUtils;
import com.orchestrator.orchestrator.utils.constants.UnlockableStatusConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SymphonyUtilsImpl implements SymphonyUtils {
    private final GeneralUtils generalUtils;

    @Override
    public Symphony buildDomainFromCreateRequestDto(SymphonyCreateRequestDto symphonyCreateRequestDto) throws IllegalAccessException {
        Composer composer = new Composer();
        composer.setIdComposer(symphonyCreateRequestDto.getIdComposer());
        Symphony symphony = new Symphony();
        symphony.setComposer(composer);
        symphony.setStatus(UnlockableStatusConstants.ACTIVE.getValue());
        generalUtils.mapFields(symphonyCreateRequestDto, symphony);
        return symphony;
    }

    @Override
    public Symphony buildDomainFromUpdateRequestDto(SymphonyUpdateRequestDto symphonyUpdateRequestDto) throws IllegalAccessException {
        Symphony symphony = new Symphony();
        if (symphonyUpdateRequestDto.getIdComposer() != null) {
            Composer composer = new Composer();
            composer.setIdComposer(symphonyUpdateRequestDto.getIdComposer());
            symphony.setComposer(composer);
        }
        generalUtils.mapFields(symphonyUpdateRequestDto, symphony);
        return symphony;
    }

    @Override
    public Symphony buildDomainFromChangeRequestDto(SymphonyChangeRequestDto symphonyChangeRequestDto) throws IllegalAccessException {
        Composer composer = new Composer();
        composer.setIdComposer(symphonyChangeRequestDto.getIdComposer());
        Symphony symphony = new Symphony();
        symphony.setComposer(composer);
        generalUtils.mapFields(symphonyChangeRequestDto, symphony);
        return symphony;
    }

    @Override
    public SymphonyCreateRequestDto buildCreateRequestDtoFromDomain(Symphony symphony) throws IllegalAccessException {
        SymphonyCreateRequestDto symphonyCreateRequestDto = new SymphonyCreateRequestDto();
        symphonyCreateRequestDto.setIdComposer(symphony.getComposer().getIdComposer());
        generalUtils.mapFields(symphony, symphonyCreateRequestDto);
        return symphonyCreateRequestDto;
    }

    @Override
    public SymphonyUpdateRequestDto buildUpdateRequestDtoFromDomain(Symphony symphony) throws IllegalAccessException {
        SymphonyUpdateRequestDto symphonyUpdateRequestDto = new SymphonyUpdateRequestDto();
        symphonyUpdateRequestDto.setIdComposer(symphony.getComposer().getIdComposer());
        generalUtils.mapFields(symphony, symphonyUpdateRequestDto);
        return symphonyUpdateRequestDto;
    }

    @Override
    public SymphonyChangeRequestDto buildChangeRequestDtoFromDomain(Symphony symphony) throws IllegalAccessException {
        SymphonyChangeRequestDto symphonyChangeRequestDto = new SymphonyChangeRequestDto();
        symphonyChangeRequestDto.setIdComposer(symphony.getComposer().getIdComposer());
        generalUtils.mapFields(symphony, symphonyChangeRequestDto);
        return symphonyChangeRequestDto;
    }
}
