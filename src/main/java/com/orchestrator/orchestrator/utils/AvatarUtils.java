package com.orchestrator.orchestrator.utils;

import com.orchestrator.orchestrator.model.Avatar;
import com.orchestrator.orchestrator.model.dto.avatar.request.AvatarChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.avatar.request.AvatarCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.avatar.request.AvatarUpdateRequestDto;

public interface AvatarUtils {
    Avatar buildDomainFromCreateRequestDto(AvatarCreateRequestDto avatarCreateRequestDto) throws IllegalAccessException;
    Avatar buildDomainFromUpdateRequestDto(AvatarUpdateRequestDto avatarUpdateRequestDto) throws IllegalAccessException;
    Avatar buildDomainFromChangeRequestDto(AvatarChangeRequestDto avatarChangeRequestDto) throws IllegalAccessException;
    AvatarCreateRequestDto buildCreateRequestDtoFromDomain(Avatar avatar) throws IllegalAccessException;
    AvatarUpdateRequestDto buildUpdateRequestDtoFromDomain(Avatar avatar) throws IllegalAccessException;
    AvatarChangeRequestDto buildChangeRequestDtoFromDomain(Avatar avatar) throws IllegalAccessException;
}
