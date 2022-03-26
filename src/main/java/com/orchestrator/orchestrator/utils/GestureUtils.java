package com.orchestrator.orchestrator.utils;

import com.orchestrator.orchestrator.model.Gesture;
import com.orchestrator.orchestrator.model.dto.gesture.request.GestureChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.gesture.request.GestureCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.gesture.request.GestureUpdateRequestDto;

public interface GestureUtils {
    Gesture buildDomainFromCreateRequestDto(GestureCreateRequestDto gestureCreateRequestDto) throws IllegalAccessException;
    Gesture buildDomainFromUpdateRequestDto(GestureUpdateRequestDto gestureUpdateRequestDto) throws IllegalAccessException;
    Gesture buildDomainFromChangeRequestDto(GestureChangeRequestDto gestureChangeRequestDto) throws IllegalAccessException;
    GestureCreateRequestDto buildCreateRequestDtoFromDomain(Gesture gesture) throws IllegalAccessException;
    GestureUpdateRequestDto buildUpdateRequestDtoFromDomain(Gesture gesture) throws IllegalAccessException;
    GestureChangeRequestDto buildChangeRequestDtoFromDomain(Gesture gesture) throws IllegalAccessException;
}
