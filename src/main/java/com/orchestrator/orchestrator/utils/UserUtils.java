package com.orchestrator.orchestrator.utils;

import com.orchestrator.orchestrator.model.User;
import com.orchestrator.orchestrator.model.dto.user.request.UserChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.user.request.UserCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.user.request.UserRegisterRequestDto;
import com.orchestrator.orchestrator.model.dto.user.request.UserUpdateRequestDto;

public interface UserUtils {
    User buildDomainFromCreateRequestDto(UserCreateRequestDto userCreateRequestDto) throws IllegalAccessException;
    User buildDomainFromUpdateRequestDto(UserUpdateRequestDto userUpdateRequestDto) throws IllegalAccessException;
    User buildDomainFromChangeRequestDto(UserChangeRequestDto userChangeRequestDto) throws IllegalAccessException;
    User buildDomainFromRegisterRequestDto(UserRegisterRequestDto userRegisterRequestDto) throws IllegalAccessException;
    UserCreateRequestDto buildCreateRequestDtoFromDomain(User user) throws IllegalAccessException;
    UserUpdateRequestDto buildUpdateRequestDtoFromDomain(User user) throws IllegalAccessException;
    UserChangeRequestDto buildChangeRequestDtoFromDomain(User user) throws IllegalAccessException;
    UserRegisterRequestDto buildRegisterRequestDtoFromDomain(User user) throws IllegalAccessException;
}
