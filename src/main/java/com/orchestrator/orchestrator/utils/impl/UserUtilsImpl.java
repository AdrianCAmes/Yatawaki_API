package com.orchestrator.orchestrator.utils.impl;

import com.orchestrator.orchestrator.model.User;
import com.orchestrator.orchestrator.model.UserStatistics;
import com.orchestrator.orchestrator.model.dto.user.request.UserChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.user.request.UserCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.user.request.UserRegisterRequestDto;
import com.orchestrator.orchestrator.model.dto.user.request.UserUpdateRequestDto;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.UserUtils;
import com.orchestrator.orchestrator.utils.constants.NumericConstants;
import com.orchestrator.orchestrator.utils.constants.UserStatusConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtilsImpl implements UserUtils {
    private final GeneralUtils generalUtils;

    @Override
    public User buildDomainFromCreateRequestDto(UserCreateRequestDto userCreateRequestDto) throws IllegalAccessException {
        UserStatistics userStatistics = new UserStatistics();
        userStatistics.setIdUserStatistics(userCreateRequestDto.getIdUserStatistics());
        User user = new User();
        user.setUserStatistics(userStatistics);
        user.setCurrencyOwned(NumericConstants.ZERO.getValue());
        user.setStatus(UserStatusConstants.ACTIVE.getValue());
        generalUtils.mapFields(userCreateRequestDto, user);
        return user;
    }

    @Override
    public User buildDomainFromUpdateRequestDto(UserUpdateRequestDto userUpdateRequestDto) throws IllegalAccessException {
        UserStatistics userStatistics = new UserStatistics();
        userStatistics.setIdUserStatistics(userUpdateRequestDto.getIdUserStatistics());
        User user = new User();
        user.setUserStatistics(userStatistics);
        generalUtils.mapFields(userUpdateRequestDto, user);
        return user;
    }

    @Override
    public User buildDomainFromChangeRequestDto(UserChangeRequestDto userChangeRequestDto) throws IllegalAccessException {
        UserStatistics userStatistics = new UserStatistics();
        userStatistics.setIdUserStatistics(userChangeRequestDto.getIdUserStatistics());
        User user = new User();
        user.setUserStatistics(userStatistics);
        generalUtils.mapFields(userChangeRequestDto, user);
        return user;
    }

    @Override
    public User buildDomainFromRegisterRequestDto(UserRegisterRequestDto userRegisterRequestDto) throws IllegalAccessException {
        User user = new User();
        user.setCurrencyOwned(NumericConstants.ZERO.getValue());
        user.setStatus(UserStatusConstants.ACTIVE.getValue());
        generalUtils.mapFields(userRegisterRequestDto, user);
        return user;
    }

    @Override
    public UserCreateRequestDto buildCreateRequestDtoFromDomain(User user) throws IllegalAccessException {
        UserCreateRequestDto userCreateRequestDto = new UserCreateRequestDto();
        userCreateRequestDto.setIdUserStatistics(user.getUserStatistics().getIdUserStatistics());
        generalUtils.mapFields(user, userCreateRequestDto);
        return userCreateRequestDto;
    }

    @Override
    public UserUpdateRequestDto buildUpdateRequestDtoFromDomain(User user) throws IllegalAccessException {
        UserUpdateRequestDto userUpdateRequestDto = new UserUpdateRequestDto();
        userUpdateRequestDto.setIdUserStatistics(user.getUserStatistics().getIdUserStatistics());
        generalUtils.mapFields(user, userUpdateRequestDto);
        return userUpdateRequestDto;
    }

    @Override
    public UserChangeRequestDto buildChangeRequestDtoFromDomain(User user) throws IllegalAccessException {
        UserChangeRequestDto userChangeRequestDto = new UserChangeRequestDto();
        userChangeRequestDto.setIdUserStatistics(user.getUserStatistics().getIdUserStatistics());
        generalUtils.mapFields(user, userChangeRequestDto);
        return userChangeRequestDto;
    }

    @Override
    public UserRegisterRequestDto buildRegisterRequestDtoFromDomain(User user) throws IllegalAccessException {
        UserRegisterRequestDto userRegisterRequestDto = new UserRegisterRequestDto();
        generalUtils.mapFields(user, userRegisterRequestDto);
        return userRegisterRequestDto;
    }
}
