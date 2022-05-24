package com.orchestrator.orchestrator.utils.impl;

import com.orchestrator.orchestrator.model.Rank;
import com.orchestrator.orchestrator.model.User;
import com.orchestrator.orchestrator.model.UserRank;
import com.orchestrator.orchestrator.model.dto.userrank.request.UserRankChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.userrank.request.UserRankCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.userrank.request.UserRankUpdateRequestDto;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.UserRankUtils;
import com.orchestrator.orchestrator.utils.constants.NumericConstants;
import com.orchestrator.orchestrator.utils.constants.UserRankStatusConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class UserRankUtilsImpl implements UserRankUtils {
    private final GeneralUtils generalUtils;

    @Override
    public UserRank buildDomainFromCreateRequestDto(UserRankCreateRequestDto userRankCreateRequestDto) throws IllegalAccessException {
        User user = new User();
        user.setIdUser(userRankCreateRequestDto.getIdUser());
        Rank rank = new Rank();
        rank.setIdRank(userRankCreateRequestDto.getIdRank());
        UserRank userRank = new UserRank();
        userRank.setUser(user);
        userRank.setRank(rank);
        userRank.setStartDate(LocalDate.now());
        userRank.setCurrentExperience(NumericConstants.ZERO.getValue());
        userRank.setStatus(UserRankStatusConstants.ACTIVE.getValue());
        generalUtils.mapFields(userRankCreateRequestDto, userRank);
        return userRank;
    }

    @Override
    public UserRank buildDomainFromUpdateRequestDto(UserRankUpdateRequestDto userRankUpdateRequestDto) throws IllegalAccessException {
        UserRank userRank = new UserRank();
        if (userRankUpdateRequestDto.getIdUser() != null) {
            User user = new User();
            user.setIdUser(userRankUpdateRequestDto.getIdUser());
            userRank.setUser(user);
        }
        if (userRankUpdateRequestDto.getIdRank() != null) {
            Rank rank = new Rank();
            rank.setIdRank(userRankUpdateRequestDto.getIdRank());
            userRank.setRank(rank);
        }
        generalUtils.mapFields(userRankUpdateRequestDto, userRank);
        return userRank;
    }

    @Override
    public UserRank buildDomainFromChangeRequestDto(UserRankChangeRequestDto userRankChangeRequestDto) throws IllegalAccessException {
        User user = new User();
        user.setIdUser(userRankChangeRequestDto.getIdUser());
        Rank rank = new Rank();
        rank.setIdRank(userRankChangeRequestDto.getIdRank());
        UserRank userRank = new UserRank();
        userRank.setUser(user);
        userRank.setRank(rank);
        generalUtils.mapFields(userRankChangeRequestDto, userRank);
        return userRank;
    }

    @Override
    public UserRankCreateRequestDto buildCreateRequestDtoFromDomain(UserRank userRank) throws IllegalAccessException {
        UserRankCreateRequestDto userRankCreateRequestDto = new UserRankCreateRequestDto();
        userRankCreateRequestDto.setIdUser(userRank.getUser().getIdUser());
        userRankCreateRequestDto.setIdRank(userRank.getRank().getIdRank());
        generalUtils.mapFields(userRank, userRankCreateRequestDto);
        return userRankCreateRequestDto;
    }

    @Override
    public UserRankUpdateRequestDto buildUpdateRequestDtoFromDomain(UserRank userRank) throws IllegalAccessException {
        UserRankUpdateRequestDto userRankUpdateRequestDto = new UserRankUpdateRequestDto();
        userRankUpdateRequestDto.setIdUser(userRank.getUser().getIdUser());
        userRankUpdateRequestDto.setIdRank(userRank.getRank().getIdRank());
        generalUtils.mapFields(userRank, userRankUpdateRequestDto);
        return userRankUpdateRequestDto;
    }

    @Override
    public UserRankChangeRequestDto buildChangeRequestDtoFromDomain(UserRank userRank) throws IllegalAccessException {
        UserRankChangeRequestDto userRankChangeRequestDto = new UserRankChangeRequestDto();
        userRankChangeRequestDto.setIdUser(userRank.getUser().getIdUser());
        userRankChangeRequestDto.setIdRank(userRank.getRank().getIdRank());
        generalUtils.mapFields(userRank, userRankChangeRequestDto);
        return userRankChangeRequestDto;
    }
}
