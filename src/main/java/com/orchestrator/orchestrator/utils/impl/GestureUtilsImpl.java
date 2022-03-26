package com.orchestrator.orchestrator.utils.impl;

import com.orchestrator.orchestrator.model.Gesture;
import com.orchestrator.orchestrator.model.dto.gesture.request.GestureChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.gesture.request.GestureCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.gesture.request.GestureUpdateRequestDto;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.GestureUtils;
import com.orchestrator.orchestrator.utils.constants.GestureStatusConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GestureUtilsImpl implements GestureUtils {
    private final GeneralUtils generalUtils;

    @Override
    public Gesture buildDomainFromCreateRequestDto(GestureCreateRequestDto gestureCreateRequestDto) throws IllegalAccessException {
        Gesture gesture = new Gesture();
        gesture.setStatus(GestureStatusConstants.ACTIVE.getValue());
        generalUtils.mapFields(gestureCreateRequestDto, gesture);
        return gesture;
    }

    @Override
    public Gesture buildDomainFromUpdateRequestDto(GestureUpdateRequestDto gestureUpdateRequestDto) throws IllegalAccessException {
        Gesture gesture = new Gesture();
        generalUtils.mapFields(gestureUpdateRequestDto, gesture);
        return gesture;
    }

    @Override
    public Gesture buildDomainFromChangeRequestDto(GestureChangeRequestDto gestureChangeRequestDto) throws IllegalAccessException {
        Gesture gesture = new Gesture();
        generalUtils.mapFields(gestureChangeRequestDto, gesture);
        return gesture;
    }

    @Override
    public GestureCreateRequestDto buildCreateRequestDtoFromDomain(Gesture gesture) throws IllegalAccessException {
        GestureCreateRequestDto gestureCreateRequestDto = new GestureCreateRequestDto();
        generalUtils.mapFields(gesture, gestureCreateRequestDto);
        return gestureCreateRequestDto;
    }

    @Override
    public GestureUpdateRequestDto buildUpdateRequestDtoFromDomain(Gesture gesture) throws IllegalAccessException {
        GestureUpdateRequestDto gestureUpdateRequestDto = new GestureUpdateRequestDto();
        generalUtils.mapFields(gesture, gestureUpdateRequestDto);
        return gestureUpdateRequestDto;
    }

    @Override
    public GestureChangeRequestDto buildChangeRequestDtoFromDomain(Gesture gesture) throws IllegalAccessException {
        GestureChangeRequestDto gestureChangeRequestDto = new GestureChangeRequestDto();
        generalUtils.mapFields(gesture, gestureChangeRequestDto);
        return gestureChangeRequestDto;
    }
}
