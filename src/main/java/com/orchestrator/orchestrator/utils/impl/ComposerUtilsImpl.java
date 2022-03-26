package com.orchestrator.orchestrator.utils.impl;

import com.orchestrator.orchestrator.model.Composer;
import com.orchestrator.orchestrator.model.dto.composer.request.ComposerChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.composer.request.ComposerCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.composer.request.ComposerUpdateRequestDto;
import com.orchestrator.orchestrator.utils.ComposerUtils;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.constants.ComposerStatusConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ComposerUtilsImpl implements ComposerUtils {
    private final GeneralUtils generalUtils;

    @Override
    public Composer buildDomainFromCreateRequestDto(ComposerCreateRequestDto composerCreateRequestDto) throws IllegalAccessException {
        Composer composer = new Composer();
        composer.setStatus(ComposerStatusConstants.ACTIVE.getValue());
        generalUtils.mapFields(composerCreateRequestDto, composer);
        return composer;
    }

    @Override
    public Composer buildDomainFromUpdateRequestDto(ComposerUpdateRequestDto composerUpdateRequestDto) throws IllegalAccessException {
        Composer composer = new Composer();
        generalUtils.mapFields(composerUpdateRequestDto, composer);
        return composer;
    }

    @Override
    public Composer buildDomainFromChangeRequestDto(ComposerChangeRequestDto composerChangeRequestDto) throws IllegalAccessException {
        Composer composer = new Composer();
        generalUtils.mapFields(composerChangeRequestDto, composer);
        return composer;
    }

    @Override
    public ComposerCreateRequestDto buildCreateRequestDtoFromDomain(Composer composer) throws IllegalAccessException {
        ComposerCreateRequestDto composerCreateRequestDto = new ComposerCreateRequestDto();
        generalUtils.mapFields(composer, composerCreateRequestDto);
        return composerCreateRequestDto;
    }

    @Override
    public ComposerUpdateRequestDto buildUpdateRequestDtoFromDomain(Composer composer) throws IllegalAccessException {
        ComposerUpdateRequestDto composerUpdateRequestDto = new ComposerUpdateRequestDto();
        generalUtils.mapFields(composer, composerUpdateRequestDto);
        return composerUpdateRequestDto;
    }

    @Override
    public ComposerChangeRequestDto buildChangeRequestDtoFromDomain(Composer composer) throws IllegalAccessException {
        ComposerChangeRequestDto composerChangeRequestDto = new ComposerChangeRequestDto();
        generalUtils.mapFields(composer, composerChangeRequestDto);
        return composerChangeRequestDto;
    }
}
