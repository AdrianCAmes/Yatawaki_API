package com.orchestrator.orchestrator.utils.impl;

import com.orchestrator.orchestrator.model.Unlockable;
import com.orchestrator.orchestrator.model.dto.unlockable.request.UnlockableChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.unlockable.request.UnlockableCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.unlockable.request.UnlockableUpdateRequestDto;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.UnlockableUtils;
import com.orchestrator.orchestrator.utils.constants.UnlockableStatusConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UnlockableUtilsImpl implements UnlockableUtils {
    private final GeneralUtils generalUtils;

    @Override
    public Unlockable buildDomainFromCreateRequestDto(UnlockableCreateRequestDto unlockableCreateRequestDto) throws IllegalAccessException {
        Unlockable unlockable = new Unlockable();
        unlockable.setStatus(UnlockableStatusConstants.ACTIVE.getValue());
        generalUtils.mapFields(unlockableCreateRequestDto, unlockable);
        return unlockable;
    }

    @Override
    public Unlockable buildDomainFromUpdateRequestDto(UnlockableUpdateRequestDto unlockableUpdateRequestDto) throws IllegalAccessException {
        Unlockable unlockable = new Unlockable();
        generalUtils.mapFields(unlockableUpdateRequestDto, unlockable);
        return unlockable;
    }

    @Override
    public Unlockable buildDomainFromChangeRequestDto(UnlockableChangeRequestDto unlockableChangeRequestDto) throws IllegalAccessException {
        Unlockable unlockable = new Unlockable();
        generalUtils.mapFields(unlockableChangeRequestDto, unlockable);
        return unlockable;
    }

    @Override
    public UnlockableCreateRequestDto buildCreateRequestDtoFromDomain(Unlockable unlockable) throws IllegalAccessException {
        UnlockableCreateRequestDto unlockableCreateRequestDto = new UnlockableCreateRequestDto();
        generalUtils.mapFields(unlockable, unlockableCreateRequestDto);
        return unlockableCreateRequestDto;
    }

    @Override
    public UnlockableUpdateRequestDto buildUpdateRequestDtoFromDomain(Unlockable unlockable) throws IllegalAccessException {
        UnlockableUpdateRequestDto unlockableUpdateRequestDto = new UnlockableUpdateRequestDto();
        generalUtils.mapFields(unlockable, unlockableUpdateRequestDto);
        return unlockableUpdateRequestDto;
    }

    @Override
    public UnlockableChangeRequestDto buildChangeRequestDtoFromDomain(Unlockable unlockable) throws IllegalAccessException {
        UnlockableChangeRequestDto unlockableChangeRequestDto = new UnlockableChangeRequestDto();
        generalUtils.mapFields(unlockable, unlockableChangeRequestDto);
        return unlockableChangeRequestDto;
    }
}
