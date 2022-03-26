package com.orchestrator.orchestrator.utils.impl;

import com.orchestrator.orchestrator.model.UserStatistics;
import com.orchestrator.orchestrator.model.dto.userstatistics.request.UserStatisticsChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.userstatistics.request.UserStatisticsCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.userstatistics.request.UserStatisticsUpdateRequestDto;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.UserStatisticsUtils;
import com.orchestrator.orchestrator.utils.constants.NumericConstants;
import com.orchestrator.orchestrator.utils.constants.UserStatisticsStatusConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserStatisticsUtilsImpl implements UserStatisticsUtils {
    private final GeneralUtils generalUtils;

    @Override
    public UserStatistics buildDomainFromCreateRequestDto(UserStatisticsCreateRequestDto userStatisticsCreateRequestDto) throws IllegalAccessException {
        UserStatistics userStatistics = new UserStatistics();
        userStatistics.setConcertsOrchestrated(NumericConstants.ZERO.getValue());
        userStatistics.setOrchestrationAccuracy(NumericConstants.ZERO.getValue().doubleValue());
        userStatistics.setTriviasPlayed(NumericConstants.ZERO.getValue());
        userStatistics.setTriviasWon(NumericConstants.ZERO.getValue());
        userStatistics.setStatus(UserStatisticsStatusConstants.ACTIVE.getValue());
        generalUtils.mapFields(userStatisticsCreateRequestDto, userStatistics);
        return userStatistics;
    }

    @Override
    public UserStatistics buildDomainFromUpdateRequestDto(UserStatisticsUpdateRequestDto userStatisticsUpdateRequestDto) throws IllegalAccessException {
        UserStatistics userStatistics = new UserStatistics();
        generalUtils.mapFields(userStatisticsUpdateRequestDto, userStatistics);
        return userStatistics;
    }

    @Override
    public UserStatistics buildDomainFromChangeRequestDto(UserStatisticsChangeRequestDto userStatisticsChangeRequestDto) throws IllegalAccessException {
        UserStatistics userStatistics = new UserStatistics();
        generalUtils.mapFields(userStatisticsChangeRequestDto, userStatistics);
        return userStatistics;
    }

    @Override
    public UserStatisticsCreateRequestDto buildCreateRequestDtoFromDomain(UserStatistics userStatistics) throws IllegalAccessException {
        UserStatisticsCreateRequestDto userStatisticsCreateRequestDto = new UserStatisticsCreateRequestDto();
        generalUtils.mapFields(userStatistics, userStatisticsCreateRequestDto);
        return userStatisticsCreateRequestDto;
    }

    @Override
    public UserStatisticsUpdateRequestDto buildUpdateRequestDtoFromDomain(UserStatistics userStatistics) throws IllegalAccessException {
        UserStatisticsUpdateRequestDto userStatisticsUpdateRequestDto = new UserStatisticsUpdateRequestDto();
        generalUtils.mapFields(userStatistics, userStatisticsUpdateRequestDto);
        return userStatisticsUpdateRequestDto;
    }

    @Override
    public UserStatisticsChangeRequestDto buildChangeRequestDtoFromDomain(UserStatistics userStatistics) throws IllegalAccessException {
        UserStatisticsChangeRequestDto userStatisticsChangeRequestDto = new UserStatisticsChangeRequestDto();
        generalUtils.mapFields(userStatistics, userStatisticsChangeRequestDto);
        return userStatisticsChangeRequestDto;
    }
}
