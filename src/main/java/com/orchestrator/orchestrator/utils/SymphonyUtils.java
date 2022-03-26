package com.orchestrator.orchestrator.utils;

import com.orchestrator.orchestrator.model.Symphony;
import com.orchestrator.orchestrator.model.dto.symphony.request.SymphonyChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.symphony.request.SymphonyCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.symphony.request.SymphonyUpdateRequestDto;

public interface SymphonyUtils {
    Symphony buildDomainFromCreateRequestDto(SymphonyCreateRequestDto symphonyCreateRequestDto) throws IllegalAccessException;
    Symphony buildDomainFromUpdateRequestDto(SymphonyUpdateRequestDto symphonyUpdateRequestDto) throws IllegalAccessException;
    Symphony buildDomainFromChangeRequestDto(SymphonyChangeRequestDto symphonyChangeRequestDto) throws IllegalAccessException;
    SymphonyCreateRequestDto buildCreateRequestDtoFromDomain(Symphony symphony) throws IllegalAccessException;
    SymphonyUpdateRequestDto buildUpdateRequestDtoFromDomain(Symphony symphony) throws IllegalAccessException;
    SymphonyChangeRequestDto buildChangeRequestDtoFromDomain(Symphony symphony) throws IllegalAccessException;
}
