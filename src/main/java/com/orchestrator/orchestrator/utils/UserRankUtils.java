package com.orchestrator.orchestrator.utils;

import com.orchestrator.orchestrator.model.UserRank;
import com.orchestrator.orchestrator.model.dto.userrank.request.UserRankChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.userrank.request.UserRankCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.userrank.request.UserRankUpdateRequestDto;

public interface UserRankUtils {
    UserRank buildDomainFromCreateRequestDto(UserRankCreateRequestDto userRankCreateRequestDto) throws IllegalAccessException;
    UserRank buildDomainFromUpdateRequestDto(UserRankUpdateRequestDto userRankUpdateRequestDto) throws IllegalAccessException;
    UserRank buildDomainFromChangeRequestDto(UserRankChangeRequestDto userRankChangeRequestDto) throws IllegalAccessException;
    UserRankCreateRequestDto buildCreateRequestDtoFromDomain(UserRank userRank) throws IllegalAccessException;
    UserRankUpdateRequestDto buildUpdateRequestDtoFromDomain(UserRank userRank) throws IllegalAccessException;
    UserRankChangeRequestDto buildChangeRequestDtoFromDomain(UserRank userRank) throws IllegalAccessException;
}
