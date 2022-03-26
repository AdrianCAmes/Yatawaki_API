package com.orchestrator.orchestrator.utils;

import com.orchestrator.orchestrator.model.Unlockable;
import com.orchestrator.orchestrator.model.dto.unlockable.request.UnlockableChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.unlockable.request.UnlockableCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.unlockable.request.UnlockableUpdateRequestDto;

public interface UnlockableUtils {
    Unlockable buildDomainFromCreateRequestDto(UnlockableCreateRequestDto unlockableCreateRequestDto) throws IllegalAccessException;
    Unlockable buildDomainFromUpdateRequestDto(UnlockableUpdateRequestDto unlockableUpdateRequestDto) throws IllegalAccessException;
    Unlockable buildDomainFromChangeRequestDto(UnlockableChangeRequestDto unlockableChangeRequestDto) throws IllegalAccessException;
    UnlockableCreateRequestDto buildCreateRequestDtoFromDomain(Unlockable unlockable) throws IllegalAccessException;
    UnlockableUpdateRequestDto buildUpdateRequestDtoFromDomain(Unlockable unlockable) throws IllegalAccessException;
    UnlockableChangeRequestDto buildChangeRequestDtoFromDomain(Unlockable unlockable) throws IllegalAccessException;
}
