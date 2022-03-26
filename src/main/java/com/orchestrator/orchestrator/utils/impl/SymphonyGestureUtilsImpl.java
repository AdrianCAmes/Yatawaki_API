package com.orchestrator.orchestrator.utils.impl;

import com.orchestrator.orchestrator.model.Gesture;
import com.orchestrator.orchestrator.model.Symphony;
import com.orchestrator.orchestrator.model.SymphonyGesture;
import com.orchestrator.orchestrator.model.dto.symphonygesture.request.SymphonyGestureChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.symphonygesture.request.SymphonyGestureCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.symphonygesture.request.SymphonyGestureUpdateRequestDto;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.SymphonyGestureUtils;
import com.orchestrator.orchestrator.utils.constants.SymphonyGestureStatusConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SymphonyGestureUtilsImpl implements SymphonyGestureUtils {
    private final GeneralUtils generalUtils;

    @Override
    public SymphonyGesture buildDomainFromCreateRequestDto(SymphonyGestureCreateRequestDto symphonyGestureCreateRequestDto) throws IllegalAccessException {
        Symphony symphony = new Symphony();
        symphony.setIdUnlockable(symphonyGestureCreateRequestDto.getIdSymphony());
        Gesture gesture = new Gesture();
        gesture.setIdGesture(symphonyGestureCreateRequestDto.getIdGesture());
        SymphonyGesture symphonyGesture = new SymphonyGesture();
        symphonyGesture.setSymphony(symphony);
        symphonyGesture.setGesture(gesture);
        symphonyGesture.setStatus(SymphonyGestureStatusConstants.ACTIVE.getValue());
        generalUtils.mapFields(symphonyGestureCreateRequestDto, symphonyGesture);
        return symphonyGesture;
    }

    @Override
    public SymphonyGesture buildDomainFromUpdateRequestDto(SymphonyGestureUpdateRequestDto symphonyGestureUpdateRequestDto) throws IllegalAccessException {
        Symphony symphony = new Symphony();
        symphony.setIdUnlockable(symphonyGestureUpdateRequestDto.getIdSymphony());
        Gesture gesture = new Gesture();
        gesture.setIdGesture(symphonyGestureUpdateRequestDto.getIdGesture());
        SymphonyGesture symphonyGesture = new SymphonyGesture();
        symphonyGesture.setSymphony(symphony);
        symphonyGesture.setGesture(gesture);
        generalUtils.mapFields(symphonyGestureUpdateRequestDto, symphonyGesture);
        return symphonyGesture;
    }

    @Override
    public SymphonyGesture buildDomainFromChangeRequestDto(SymphonyGestureChangeRequestDto symphonyGestureChangeRequestDto) throws IllegalAccessException {
        Symphony symphony = new Symphony();
        symphony.setIdUnlockable(symphonyGestureChangeRequestDto.getIdSymphony());
        Gesture gesture = new Gesture();
        gesture.setIdGesture(symphonyGestureChangeRequestDto.getIdGesture());
        SymphonyGesture symphonyGesture = new SymphonyGesture();
        symphonyGesture.setSymphony(symphony);
        symphonyGesture.setGesture(gesture);
        generalUtils.mapFields(symphonyGestureChangeRequestDto, symphonyGesture);
        return symphonyGesture;
    }

    @Override
    public SymphonyGestureCreateRequestDto buildCreateRequestDtoFromDomain(SymphonyGesture symphonyGesture) throws IllegalAccessException {
        SymphonyGestureCreateRequestDto symphonyGestureCreateRequestDto = new SymphonyGestureCreateRequestDto();
        symphonyGestureCreateRequestDto.setIdSymphony(symphonyGesture.getSymphony().getIdUnlockable());
        symphonyGestureCreateRequestDto.setIdGesture(symphonyGesture.getGesture().getIdGesture());
        generalUtils.mapFields(symphonyGesture, symphonyGestureCreateRequestDto);
        return symphonyGestureCreateRequestDto;
    }

    @Override
    public SymphonyGestureUpdateRequestDto buildUpdateRequestDtoFromDomain(SymphonyGesture symphonyGesture) throws IllegalAccessException {
        SymphonyGestureUpdateRequestDto symphonyGestureUpdateRequestDto = new SymphonyGestureUpdateRequestDto();
        symphonyGestureUpdateRequestDto.setIdSymphony(symphonyGesture.getSymphony().getIdUnlockable());
        symphonyGestureUpdateRequestDto.setIdGesture(symphonyGesture.getGesture().getIdGesture());
        generalUtils.mapFields(symphonyGesture, symphonyGestureUpdateRequestDto);
        return symphonyGestureUpdateRequestDto;
    }

    @Override
    public SymphonyGestureChangeRequestDto buildChangeRequestDtoFromDomain(SymphonyGesture symphonyGesture) throws IllegalAccessException {
        SymphonyGestureChangeRequestDto symphonyGestureChangeRequestDto = new SymphonyGestureChangeRequestDto();
        symphonyGestureChangeRequestDto.setIdSymphony(symphonyGesture.getSymphony().getIdUnlockable());
        symphonyGestureChangeRequestDto.setIdGesture(symphonyGesture.getGesture().getIdGesture());
        generalUtils.mapFields(symphonyGesture, symphonyGestureChangeRequestDto);
        return symphonyGestureChangeRequestDto;
    }
}
