package com.orchestrator.orchestrator.utils.impl;

import com.orchestrator.orchestrator.model.Unlockable;
import com.orchestrator.orchestrator.model.User;
import com.orchestrator.orchestrator.model.UserUnlockable;
import com.orchestrator.orchestrator.model.dto.userunlockable.request.UserUnlockableChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.userunlockable.request.UserUnlockableCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.userunlockable.request.UserUnlockableUpdateRequestDto;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.UserUnlockableUtils;
import com.orchestrator.orchestrator.utils.constants.UserUnlockableStatusConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class UserUnlockableUtilsImpl implements UserUnlockableUtils {
    private final GeneralUtils generalUtils;

    @Override
    public UserUnlockable buildDomainFromCreateRequestDto(UserUnlockableCreateRequestDto userUnlockableCreateRequestDto) throws IllegalAccessException {
        User user = new User();
        user.setIdUser(userUnlockableCreateRequestDto.getIdUser());
        Unlockable unlockable = new Unlockable();
        unlockable.setIdUnlockable(userUnlockableCreateRequestDto.getIdUnlockable());
        UserUnlockable userUnlockable = new UserUnlockable();
        userUnlockable.setUser(user);
        userUnlockable.setUnlockable(unlockable);
        userUnlockable.setUnlockedDate(LocalDate.now());
        userUnlockable.setStatus(UserUnlockableStatusConstants.ACTIVE.getValue());
        generalUtils.mapFields(userUnlockableCreateRequestDto, userUnlockable);
        return userUnlockable;
    }

    @Override
    public UserUnlockable buildDomainFromUpdateRequestDto(UserUnlockableUpdateRequestDto userUnlockableUpdateRequestDto) throws IllegalAccessException {
        User user = new User();
        user.setIdUser(userUnlockableUpdateRequestDto.getIdUser());
        Unlockable unlockable = new Unlockable();
        unlockable.setIdUnlockable(userUnlockableUpdateRequestDto.getIdUnlockable());
        UserUnlockable userUnlockable = new UserUnlockable();
        userUnlockable.setUser(user);
        userUnlockable.setUnlockable(unlockable);
        generalUtils.mapFields(userUnlockableUpdateRequestDto, userUnlockable);
        return userUnlockable;
    }

    @Override
    public UserUnlockable buildDomainFromChangeRequestDto(UserUnlockableChangeRequestDto userUnlockableChangeRequestDto) throws IllegalAccessException {
        User user = new User();
        user.setIdUser(userUnlockableChangeRequestDto.getIdUser());
        Unlockable unlockable = new Unlockable();
        unlockable.setIdUnlockable(userUnlockableChangeRequestDto.getIdUnlockable());
        UserUnlockable userUnlockable = new UserUnlockable();
        userUnlockable.setUser(user);
        userUnlockable.setUnlockable(unlockable);
        generalUtils.mapFields(userUnlockableChangeRequestDto, userUnlockable);
        return userUnlockable;
    }

    @Override
    public UserUnlockableCreateRequestDto buildCreateRequestDtoFromDomain(UserUnlockable userUnlockable) throws IllegalAccessException {
        UserUnlockableCreateRequestDto userUnlockableCreateRequestDto = new UserUnlockableCreateRequestDto();
        userUnlockableCreateRequestDto.setIdUser(userUnlockable.getUser().getIdUser());
        userUnlockableCreateRequestDto.setIdUnlockable(userUnlockable.getUnlockable().getIdUnlockable());
        generalUtils.mapFields(userUnlockable, userUnlockableCreateRequestDto);
        return userUnlockableCreateRequestDto;
    }

    @Override
    public UserUnlockableUpdateRequestDto buildUpdateRequestDtoFromDomain(UserUnlockable userUnlockable) throws IllegalAccessException {
        UserUnlockableUpdateRequestDto userUnlockableUpdateRequestDto = new UserUnlockableUpdateRequestDto();
        userUnlockableUpdateRequestDto.setIdUser(userUnlockable.getUser().getIdUser());
        userUnlockableUpdateRequestDto.setIdUnlockable(userUnlockable.getUnlockable().getIdUnlockable());
        generalUtils.mapFields(userUnlockable, userUnlockableUpdateRequestDto);
        return userUnlockableUpdateRequestDto;
    }

    @Override
    public UserUnlockableChangeRequestDto buildChangeRequestDtoFromDomain(UserUnlockable userUnlockable) throws IllegalAccessException {
        UserUnlockableChangeRequestDto userUnlockableChangeRequestDto = new UserUnlockableChangeRequestDto();
        userUnlockableChangeRequestDto.setIdUser(userUnlockable.getUser().getIdUser());
        userUnlockableChangeRequestDto.setIdUnlockable(userUnlockable.getUnlockable().getIdUnlockable());
        generalUtils.mapFields(userUnlockable, userUnlockableChangeRequestDto);
        return userUnlockableChangeRequestDto;
    }
}
