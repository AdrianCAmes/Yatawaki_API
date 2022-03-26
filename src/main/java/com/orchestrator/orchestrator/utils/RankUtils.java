package com.orchestrator.orchestrator.utils;

import com.orchestrator.orchestrator.model.Rank;
import com.orchestrator.orchestrator.model.dto.rank.request.RankChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.rank.request.RankCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.rank.request.RankUpdateRequestDto;

public interface RankUtils {
    Rank buildDomainFromCreateRequestDto(RankCreateRequestDto rankCreateRequestDto) throws IllegalAccessException;
    Rank buildDomainFromUpdateRequestDto(RankUpdateRequestDto rankUpdateRequestDto) throws IllegalAccessException;
    Rank buildDomainFromChangeRequestDto(RankChangeRequestDto rankChangeRequestDto) throws IllegalAccessException;
    RankCreateRequestDto buildCreateRequestDtoFromDomain(Rank rank) throws IllegalAccessException;
    RankUpdateRequestDto buildUpdateRequestDtoFromDomain(Rank rank) throws IllegalAccessException;
    RankChangeRequestDto buildChangeRequestDtoFromDomain(Rank rank) throws IllegalAccessException;
}
