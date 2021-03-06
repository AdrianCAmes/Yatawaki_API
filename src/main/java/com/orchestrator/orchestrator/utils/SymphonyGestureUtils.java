package com.orchestrator.orchestrator.utils;

import com.orchestrator.orchestrator.model.SymphonyGesture;
import com.orchestrator.orchestrator.model.dto.symphonygesture.request.SymphonyGestureChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.symphonygesture.request.SymphonyGestureCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.symphonygesture.request.SymphonyGestureUpdateRequestDto;

public interface SymphonyGestureUtils {
    SymphonyGesture buildDomainFromCreateRequestDto(SymphonyGestureCreateRequestDto symphonyGestureCreateRequestDto) throws IllegalAccessException;
    SymphonyGesture buildDomainFromUpdateRequestDto(SymphonyGestureUpdateRequestDto symphonyGestureUpdateRequestDto) throws IllegalAccessException;
    SymphonyGesture buildDomainFromChangeRequestDto(SymphonyGestureChangeRequestDto symphonyGestureChangeRequestDto) throws IllegalAccessException;
    SymphonyGestureCreateRequestDto buildCreateRequestDtoFromDomain(SymphonyGesture symphonyGesture) throws IllegalAccessException;
    SymphonyGestureUpdateRequestDto buildUpdateRequestDtoFromDomain(SymphonyGesture symphonyGesture) throws IllegalAccessException;
    SymphonyGestureChangeRequestDto buildChangeRequestDtoFromDomain(SymphonyGesture symphonyGesture) throws IllegalAccessException;
}
