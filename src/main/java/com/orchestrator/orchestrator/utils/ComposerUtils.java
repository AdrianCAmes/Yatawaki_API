package com.orchestrator.orchestrator.utils;

import com.orchestrator.orchestrator.model.Composer;
import com.orchestrator.orchestrator.model.dto.composer.request.ComposerChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.composer.request.ComposerCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.composer.request.ComposerUpdateRequestDto;

public interface ComposerUtils {
    Composer buildDomainFromCreateRequestDto(ComposerCreateRequestDto composerCreateRequestDto) throws IllegalAccessException;
    Composer buildDomainFromUpdateRequestDto(ComposerUpdateRequestDto composerUpdateRequestDto) throws IllegalAccessException;
    Composer buildDomainFromChangeRequestDto(ComposerChangeRequestDto composerChangeRequestDto) throws IllegalAccessException;
    ComposerCreateRequestDto buildCreateRequestDtoFromDomain(Composer composer) throws IllegalAccessException;
    ComposerUpdateRequestDto buildUpdateRequestDtoFromDomain(Composer composer) throws IllegalAccessException;
    ComposerChangeRequestDto buildChangeRequestDtoFromDomain(Composer composer) throws IllegalAccessException;
}
