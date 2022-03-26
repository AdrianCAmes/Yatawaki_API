package com.orchestrator.orchestrator.utils.impl;

import com.orchestrator.orchestrator.model.Avatar;
import com.orchestrator.orchestrator.model.dto.avatar.request.AvatarChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.avatar.request.AvatarCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.avatar.request.AvatarUpdateRequestDto;
import com.orchestrator.orchestrator.utils.AvatarUtils;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.constants.UnlockableStatusConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AvatarUtilsImpl implements AvatarUtils {
    private final GeneralUtils generalUtils;

    @Override
    public Avatar buildDomainFromCreateRequestDto(AvatarCreateRequestDto avatarCreateRequestDto) throws IllegalAccessException {
        Avatar avatar = new Avatar();
        avatar.setStatus(UnlockableStatusConstants.ACTIVE.getValue());
        generalUtils.mapFields(avatarCreateRequestDto, avatar);
        return avatar;
    }

    @Override
    public Avatar buildDomainFromUpdateRequestDto(AvatarUpdateRequestDto avatarUpdateRequestDto) throws IllegalAccessException {
        Avatar avatar = new Avatar();
        generalUtils.mapFields(avatarUpdateRequestDto, avatar);
        return avatar;
    }

    @Override
    public Avatar buildDomainFromChangeRequestDto(AvatarChangeRequestDto avatarChangeRequestDto) throws IllegalAccessException {
        Avatar avatar = new Avatar();
        generalUtils.mapFields(avatarChangeRequestDto, avatar);
        return avatar;
    }

    @Override
    public AvatarCreateRequestDto buildCreateRequestDtoFromDomain(Avatar avatar) throws IllegalAccessException {
        AvatarCreateRequestDto avatarCreateRequestDto = new AvatarCreateRequestDto();
        generalUtils.mapFields(avatarCreateRequestDto, avatar);
        return avatarCreateRequestDto;
    }

    @Override
    public AvatarUpdateRequestDto buildUpdateRequestDtoFromDomain(Avatar avatar) throws IllegalAccessException {
        AvatarUpdateRequestDto avatarUpdateRequestDto = new AvatarUpdateRequestDto();
        generalUtils.mapFields(avatarUpdateRequestDto, avatar);
        return avatarUpdateRequestDto;
    }

    @Override
    public AvatarChangeRequestDto buildChangeRequestDtoFromDomain(Avatar avatar) throws IllegalAccessException {
        AvatarChangeRequestDto avatarChangeRequestDto = new AvatarChangeRequestDto();
        generalUtils.mapFields(avatarChangeRequestDto, avatar);
        return avatarChangeRequestDto;
    }
}
